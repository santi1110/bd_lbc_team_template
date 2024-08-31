package com.amazon.ata.gym.routine.service.models.results;

import com.amazon.ata.gym.routine.service.models.ExerciseModel;

import java.util.List;

/**
 * Result class for retrieving exercises from a gym routine.
 */
public class GetRoutineExercisesResult {
    private List<ExerciseModel> exerciseList; // List of exercises in the routine

    public GetRoutineExercisesResult(Builder builder) {
        this.exerciseList = builder.exerciseList;
    }

    public List<ExerciseModel> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<ExerciseModel> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private List<ExerciseModel> exerciseList;

        public Builder withExerciseList(List<ExerciseModel> exerciseListToUse) {
            this.exerciseList = exerciseListToUse;
            return this;
        }

        public GetRoutineExercisesResult build() {
            return new GetRoutineExercisesResult(this);
        }
    }
}

