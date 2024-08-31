package com.amazon.ata.gym.routine.service.helpers;

import com.amazon.ata.gym.routine.service.dynamodb.models.ExerciseTrack;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ExerciseTrackTestHelper {
    private ExerciseTrackTestHelper() {
    }

    public static ExerciseTrack generateExerciseTrack(int sequenceNumber) {
        ExerciseTrack exerciseTrack = new ExerciseTrack();
        exerciseTrack.setEquipmentId("equipment" + sequenceNumber);
        exerciseTrack.setTrackNumber(sequenceNumber);
        exerciseTrack.setMuscleGroup("muscleGroup" + sequenceNumber);
        exerciseTrack.setExerciseName("exerciseName" + sequenceNumber);
        return exerciseTrack;
    }

    public static void assertExerciseTracksEqualExerciseModels(List<ExerciseTrack> exerciseTracks, List<ExerciseModel> exerciseModels) {
        assertEquals(exerciseTracks.size(),
                exerciseModels.size(),
                String.format("Expected exercise tracks (%s) and exercise models (%s) to match",
                        exerciseTracks,
                        exerciseModels));
        for (int i = 0; i < exerciseTracks.size(); i++) {
            assertExerciseTrackEqualsExerciseModel(
                    exerciseTracks.get(i),
                    exerciseModels.get(i),
                    String.format("Expected %dth exercise track (%s) to match corresponding exercise model (%s)",
                            i,
                            exerciseTracks.get(i),
                            exerciseModels.get(i)));
        }
    }

    public static void assertExerciseTrackEqualsExerciseModel(ExerciseTrack exerciseTrack, ExerciseModel exerciseModel) {
        String message = String.format("Expected exercise track %s to match exercise model %s", exerciseTrack, exerciseModel);
        assertExerciseTrackEqualsExerciseModel(exerciseTrack, exerciseModel, message);
    }

    public static void assertExerciseTrackEqualsExerciseModel(ExerciseTrack exerciseTrack, ExerciseModel exerciseModel, String message) {
        assertEquals(exerciseTrack.getEquipmentId(), exerciseModel.getEquipmentId(), message);
        assertEquals(exerciseTrack.getTrackNumber(), exerciseModel.getTrackNumber(), message);
        assertEquals(exerciseTrack.getMuscleGroup(), exerciseModel.getMuscleGroup(), message);
        assertEquals(exerciseTrack.getExerciseName(), exerciseModel.getExerciseName(), message);
    }
}

