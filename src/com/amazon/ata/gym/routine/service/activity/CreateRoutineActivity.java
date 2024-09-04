package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.converters.ModelConverter;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.gym.routine.service.models.requests.CreateRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.CreateRoutineResult;
import com.amazon.ata.gym.routine.service.models.RoutineModel;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;

import com.amazon.ata.gym.routine.service.util.GymRoutineServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;

import static com.amazon.ata.gym.routine.service.util.GymRoutineServiceUtils.isValidString;

/**
 * Implementation of the CreateRoutineActivity for the GymRoutineService's CreateRoutine API.
 *
 * This API allows the user to create a new routine with no exercises.
 */
public class CreateRoutineActivity implements RequestHandler<CreateRoutineRequest, CreateRoutineResult> {
    private final Logger log = LogManager.getLogger();
    private final RoutineDao routineDao;

    /**
     * Instantiates a new CreateRoutineActivity object.
     *
     * @param routineDao RoutineDao to access the routines table.
     */
    @Inject
    public CreateRoutineActivity(RoutineDao routineDao) {
        this.routineDao = routineDao;
    }

    /**
     * This method handles the incoming request by persisting a new routine
     * with the provided routine name and user ID from the request.
     * <p>
     * It then returns the newly created routine.
     * <p>
     * If the provided routine name or user ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createRoutineRequest request object containing the routine name and user ID
     *                             associated with it
     * @return createRoutineResult result object containing the API defined {@link RoutineModel}
     */
    @Override
    public CreateRoutineResult handleRequest(final CreateRoutineRequest createRoutineRequest, Context context) {
        log.info("Received CreateRoutineRequest {}", createRoutineRequest);
        String routineName = createRoutineRequest.getName();
        String customerId = createRoutineRequest.getCustomerId();

        if (!isValidString(routineName) || !isValidString(customerId)) {
            throw new InvalidAttributeValueException("Invalid characters in routine name or user ID");
        }

        Routine routine = new Routine();
        routine.setId(GymRoutineServiceUtils.generateRoutineId());
        routine.setName(routineName);
        routine.setCustomerId(customerId);
        routine.setExerciseCount(0);
        routine.setTags(createRoutineRequest.getTags() != null ? new HashSet<>(createRoutineRequest.getTags()) : null);
        routine.setExerciseList(new ArrayList<>()); // Initialize with an empty list

        routineDao.saveRoutine(routine);

        RoutineModel routineModel = new ModelConverter().toRoutineModel(routine);

        return CreateRoutineResult.builder()
                .withRoutine(routineModel)
                .build();
    }
}

