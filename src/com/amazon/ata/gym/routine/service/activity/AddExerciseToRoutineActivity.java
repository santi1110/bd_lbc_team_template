package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.converters.ModelConverter;
import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.ExerciseNotFoundException;
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.models.requests.AddExerciseToRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.AddExerciseToRoutineResult;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;
import com.amazon.ata.gym.routine.service.dao.ExerciseDao;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the AddExerciseToRoutineActivity for the GymRoutineService's AddExerciseToRoutine API.
 *
 * This API allows the user to add an exercise to their existing routine.
 */
public class AddExerciseToRoutineActivity implements RequestHandler<AddExerciseToRoutineRequest, AddExerciseToRoutineResult> {
    private final Logger log = LogManager.getLogger();
    private final RoutineDao routineDao;
    private final ExerciseDao exerciseDao;

    /**
     * Instantiates a new AddExerciseToRoutineActivity object.
     *
     * @param routineDao RoutineDao to access the routine table.
     * @param exerciseDao ExerciseDao to access the exercise table.
     */
    @Inject
    public AddExerciseToRoutineActivity(RoutineDao routineDao, ExerciseDao exerciseDao) {
        this.routineDao = routineDao;
        this.exerciseDao = exerciseDao;
    }

    /**
     * This method handles the incoming request by adding an additional exercise
     * to a routine and persisting the updated routine.
     * <p>
     * It then returns the updated exercise list of the routine.
     * <p>
     * If the routine does not exist, this should throw a RoutineNotFoundException.
     * <p>
     * If the exercise does not exist, this should throw an ExerciseNotFoundException.
     *
     * @param addExerciseToRoutineRequest request object containing the routine ID and an exercise ID and order
     *                                    to retrieve the exercise data
     * @return addExerciseToRoutineResult result object containing the routine's updated list of
     *                                     API defined {@link ExerciseModel}s
     */
    @Override
    public AddExerciseToRoutineResult handleRequest(final AddExerciseToRoutineRequest addExerciseToRoutineRequest, Context context) {
        log.info("Received AddExerciseToRoutineRequest {} ", addExerciseToRoutineRequest);

        Routine routine = routineDao.getRoutine(addExerciseToRoutineRequest.getId());
        if (routine == null) {
            throw new RoutineNotFoundException("Could not find routine with id " + addExerciseToRoutineRequest.getId());
        }

        // Fetch the exercise by ID and order
        Exercise exercise = exerciseDao.getExercise(addExerciseToRoutineRequest.getExerciseId(), addExerciseToRoutineRequest.getExerciseNumber());
        if (exercise == null) {
            throw new ExerciseNotFoundException("Could not find exercise with ID " + addExerciseToRoutineRequest.getExerciseId() + " and order " + addExerciseToRoutineRequest.getExerciseNumber());
        }

        // Convert Exercise to ExerciseModel
        ModelConverter converter = new ModelConverter();
        ExerciseModel exerciseModel = converter.toExerciseModel(exercise);

        // Add exercise to routine
        if (routine.getExerciseList() == null) {
            routine.setExerciseList(new ArrayList<>());
        }

        if (addExerciseToRoutineRequest.isQueueNext()) {
            routine.getExerciseList().add(0, exercise);  // Add to the start of the list
        } else {
            routine.getExerciseList().add(exercise);  // Add to the end of the list
        }

        // Save updated routine
        routineDao.saveRoutine(routine);

        // Return the updated exercise list
        List<ExerciseModel> updatedExerciseList = new ArrayList<>();
        for (Exercise ex : routine.getExerciseList()) {
            updatedExerciseList.add(converter.toExerciseModel(ex));
        }

        return AddExerciseToRoutineResult.builder()
                .withExerciseList(updatedExerciseList)
                .build();
    }
}
