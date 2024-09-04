package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.models.requests.GetRoutineExercisesRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineExercisesResult;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.converters.ModelConverter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetRoutineExercisesActivity for the GymRoutineService's GetRoutineExercises API.
 *
 * This API allows the user to get the list of exercises in a saved routine.
 */
public class GetRoutineExercisesActivity implements RequestHandler<GetRoutineExercisesRequest, GetRoutineExercisesResult> {
    private final Logger log = LogManager.getLogger();
    private final RoutineDao routineDao;

    /**
     * Instantiates a new GetRoutineExercisesActivity object.
     *
     * @param routineDao RoutineDao to access the routine table.
     */
    @Inject
    public GetRoutineExercisesActivity(RoutineDao routineDao) {
        this.routineDao = routineDao;
    }

    /**
     * This method handles the incoming request by retrieving the routine from the database.
     * <p>
     * It then returns the routine's exercise list.
     * <p>
     * If the routine does not exist, this should throw a RoutineNotFoundException.
     *
     * @param getRoutineExercisesRequest request object containing the routine ID
     * @return getRoutineExercisesResult result object containing the routine's list of API defined {@link ExerciseModel}s
     */
    @Override
    public GetRoutineExercisesResult handleRequest(final GetRoutineExercisesRequest getRoutineExercisesRequest, Context context) {
        log.info("Received GetRoutineExercisesRequest {}", getRoutineExercisesRequest);

        Routine routine = routineDao.getRoutine(getRoutineExercisesRequest.getRoutineId());
        if (routine == null) {
            throw new RoutineNotFoundException("Could not find routine with id " + getRoutineExercisesRequest.getRoutineId());
        }

        // Convert Exercise to ExerciseModel
        ModelConverter converter = new ModelConverter();
        List<ExerciseModel> exerciseModels = new ArrayList<>();
        for (Exercise exercise : routine.getExerciseList()) {
            exerciseModels.add(converter.toExerciseModel(exercise));
        }

        // Return the result
        return GetRoutineExercisesResult.builder()
                .withExerciseList(exerciseModels)
                .build();
    }
}

