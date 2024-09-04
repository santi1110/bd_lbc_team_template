package com.amazon.ata.gym.routine.service.helpers;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class RoutineTestHelper {
    private RoutineTestHelper() {
    }

    public static Routine generateRoutine() {
        return generateRoutineWithNExercises(1);
    }

    public static Routine generateRoutineWithNExercises(int numExercises) {
        Routine routine = new Routine();
        routine.setId("id");
        routine.setName("a routine");
        routine.setCustomerId("CustomerABC");
        routine.setTags(Collections.singleton("tag"));

        List<Exercise> exerciseTracks = new LinkedList<>();
        for (int i = 0; i < numExercises; i++) {
            exerciseTracks.add(ExerciseTestHelper.generateExercise(i));
        }
        routine.setExerciseList(exerciseTracks);
        routine.setExerciseCount(exerciseTracks.size());

        return routine;
    }
}

