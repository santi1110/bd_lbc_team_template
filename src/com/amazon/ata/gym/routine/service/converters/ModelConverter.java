package com.amazon.ata.gym.routine.service.converters;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;
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
                .withExerciseCount(routine.getExerciseCount())
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
                .withMuscleGroupName(exercise.getMuscleGroupName())
                .withExerciseOrder(exercise.getExerciseNumber())
                .withExerciseName(exercise.getExerciseName())
                .build();
    }
}



