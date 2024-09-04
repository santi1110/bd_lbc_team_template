package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.gym.routine.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.models.requests.UpdateRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.UpdateRoutineResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdateRoutineActivityTest {
    @Mock
    private RoutineDao routineDao;

    private UpdateRoutineActivity updateRoutineActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updateRoutineActivity = new UpdateRoutineActivity(routineDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesRoutineName() {
        // GIVEN
        String id = "id";
        String expectedCustomerId = "expectedCustomerId";
        String expectedName = "new name";

        UpdateRoutineRequest request = UpdateRoutineRequest.builder()
                .withRoutineId(id)
                .withCustomerId(expectedCustomerId)
                .withName(expectedName)
                .build();

        Routine startingRoutine = new Routine();
        startingRoutine.setCustomerId(expectedCustomerId);
        startingRoutine.setName("old name");
        startingRoutine.setExerciseCount(0);

        when(routineDao.getRoutine(id)).thenReturn(startingRoutine);
        doAnswer(invocation -> {
            Routine routine = invocation.getArgument(0);
            // Simulate the DAO saving the routine (update in place)
            startingRoutine.setName(routine.getName());
            startingRoutine.setTags(routine.getTags());
            return null;
        }).when(routineDao).saveRoutine(any(Routine.class));

        // WHEN
        UpdateRoutineResult result = updateRoutineActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedName, result.getRoutine().getName());
        assertEquals(expectedCustomerId, result.getRoutine().getCustomerId());

        verify(routineDao, times(1)).getRoutine(id);
        verify(routineDao, times(1)).saveRoutine(startingRoutine);
        verifyNoMoreInteractions(routineDao);
    }

    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        UpdateRoutineRequest request = UpdateRoutineRequest.builder()
                .withName("I'm illegal")
                .withCustomerId("customerId")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> updateRoutineActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_routineDoesNotExist_throwsRoutineNotFoundException() {
        // GIVEN
        String id = "id";
        UpdateRoutineRequest request = UpdateRoutineRequest.builder()
                .withRoutineId(id)
                .withName("name")
                .withCustomerId("customerId")
                .build();

        when(routineDao.getRoutine(id)).thenThrow(new RoutineNotFoundException());

        // THEN
        assertThrows(RoutineNotFoundException.class, () -> updateRoutineActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_customerIdNotMatch_throwsInvalidAttributeChangeException() {
        // GIVEN
        String id = "id";
        UpdateRoutineRequest request = UpdateRoutineRequest.builder()
                .withRoutineId(id)
                .withName("name")
                .withCustomerId("customerId")
                .build();

        Routine differentCustomerIdRoutine = new Routine();
        differentCustomerIdRoutine.setCustomerId("different");

        when(routineDao.getRoutine(id)).thenReturn(differentCustomerIdRoutine);

        // THEN
        assertThrows(InvalidAttributeChangeException.class, () -> updateRoutineActivity.handleRequest(request, null));
    }
}
