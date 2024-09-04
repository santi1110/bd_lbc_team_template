package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.models.requests.GetRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetRoutineActivityTest {
    @Mock
    private RoutineDao routineDao;

    private GetRoutineActivity getRoutineActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getRoutineActivity = new GetRoutineActivity(routineDao);
    }

    @Test
    public void handleRequest_savedRoutineFound_returnsRoutineModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedExerciseCount = 0;
        List<String> expectedTags = Lists.newArrayList("tag");

        Routine routine = new Routine();
        routine.setId(expectedId);
        routine.setName(expectedName);
        routine.setCustomerId(expectedCustomerId);
        routine.setExerciseCount(expectedExerciseCount);
        routine.setTags(Sets.newHashSet(expectedTags));

        when(routineDao.getRoutine(expectedId)).thenReturn(routine);

        GetRoutineRequest request = GetRoutineRequest.builder()
                .withRoutineId(expectedId)
                .build();

        // WHEN
        GetRoutineResult result = getRoutineActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedId, result.getRoutine().getId());
        assertEquals(expectedName, result.getRoutine().getName());
        assertEquals(expectedCustomerId, result.getRoutine().getCustomerId());
        assertEquals(expectedExerciseCount, result.getRoutine().getExerciseCount());
        assertEquals(expectedTags, result.getRoutine().getTags());
    }
}
