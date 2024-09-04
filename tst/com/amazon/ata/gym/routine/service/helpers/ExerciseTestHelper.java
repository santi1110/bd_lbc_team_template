package com.amazon.ata.gym.routine.service.helpers;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ExerciseTestHelper {
    private ExerciseTestHelper() {
    }

    public static Exercise generateExercise(int exerciseNumber) {
        Exercise exercise = new Exercise();
        exercise.setExerciseId("exercise" + exerciseNumber);
        exercise.setExerciseNumber(exerciseNumber);
        exercise.setMuscleGroupName("muscleGroup" + exerciseNumber);
        exercise.setExerciseName("exerciseName" + exerciseNumber);
        return exercise;
    }


    public static void assertExercisesEqualExerciseModels(List<Exercise> exercises, List<ExerciseModel> exerciseModels) {
        assertEquals(exercises.size(),
                exerciseModels.size(),
                String.format("Expected exercises (%s) and exercise models (%s) to match",
                        exercises,
                        exerciseModels));
        for (int i = 0; i < exercises.size(); i++) {
            assertExerciseEqualsExerciseModel(
                    exercises.get(i),
                    exerciseModels.get(i),
                    String.format("Expected %dth exercise (%s) to match corresponding exercise model (%s)",
                            i,
                            exercises.get(i),
                            exerciseModels.get(i)));
        }
    }

    public static void assertExerciseEqualsExerciseModel(Exercise exercise, ExerciseModel exerciseModel) {
        String message = String.format("Expected exercise %s to match exercise model %s", exercise, exerciseModel);
        assertExerciseEqualsExerciseModel(exercise, exerciseModel, message);
    }

    public static void assertExerciseEqualsExerciseModel(Exercise exercise, ExerciseModel exerciseModel, String message) {
        assertEquals(exercise.getExerciseId(), exerciseModel.getExerciseId(), message);
        assertEquals(exercise.getExerciseNumber(), exerciseModel.getExerciseNumber(), message);
        assertEquals(exercise.getMuscleGroupName(), exerciseModel.getMuscleGroupName(), message);
        assertEquals(exercise.getExerciseName(), exerciseModel.getExerciseName(), message);
    }
}

