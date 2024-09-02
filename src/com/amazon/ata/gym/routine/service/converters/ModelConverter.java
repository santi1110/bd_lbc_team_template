package com.amazon.ata.gym.routine.service.converters;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise; // Updated model import
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine; // Updated model import
import com.amazon.ata.gym.routine.service.models.ExerciseModel; // Updated model import
import com.amazon.ata.gym.routine.service.models.RoutineModel;

import java.util.ArrayList;

/**
 * Converts between DynamoDB models and API models.
 */
public class ModelConverter {

    /**
     * Converts a provided {@link Routine} into a {@link RoutineModel} representation.
     *
     * @param routine the routine to convert
     * @return the converted routine
     */
    public RoutineModel toRoutineModel(Routine routine) {
        return RoutineModel.builder()
                .withId(routine.getId())
                .withName(routine.getName())
                .withCustomerId(routine.getCustomerId())
                .withExerciseCount(routine.getExerciseCount()) // Updated attribute
                .withTags(routine.getTags() != null ? new ArrayList<>(routine.getTags()) : null)
                .build();
    }

    /**
     * Converts a provided {@link Exercise} into an {@link ExerciseModel} representation.
     *
     * @param exercise the exercise to convert
     * @return the converted exercise
     */
    public ExerciseModel toExerciseModel(Exercise exercise) {
        return ExerciseModel.builder()
                .withExerciseId(exercise.getExerciseId())
                .withRoutineId(exercise.getExerciseId()) // Corrected method name
                .withExerciseOrder(exercise.getExerciseOrder()) // Corrected method name
                .withExerciseName(exercise.getExerciseName()) // Corrected method name
                .build();
    }

}

