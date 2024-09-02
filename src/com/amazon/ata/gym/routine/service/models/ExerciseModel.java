package com.amazon.ata.gym.routine.service.models;

import java.util.Objects;

/**
 * Model class representing an exercise.
 */
public class ExerciseModel {
    private String exerciseId; // Changed from id to exerciseId
    private String routineId; // Changed from routine to routineId
    private int exerciseOrder; // Changed from orderNumber to exerciseOrder
    private String exerciseName; // Changed from name to exerciseName

    public ExerciseModel() {
    }

    public ExerciseModel(Builder builder) {
        this.exerciseId = builder.exerciseId;
        this.routineId = builder.routineId;
        this.exerciseOrder = builder.exerciseOrder;
        this.exerciseName = builder.exerciseName;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public int getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(int exerciseOrder) {
        this.exerciseOrder = exerciseOrder;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseModel that = (ExerciseModel) o;
        return exerciseOrder == that.exerciseOrder &&
                Objects.equals(exerciseId, that.exerciseId) &&
                Objects.equals(routineId, that.routineId) &&
                Objects.equals(exerciseName, that.exerciseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, routineId, exerciseOrder, exerciseName);
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "exerciseId='" + exerciseId + '\'' +
                ", routineId='" + routineId + '\'' +
                ", exerciseOrder=" + exerciseOrder +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String exerciseId;
        private String routineId;
        private int exerciseOrder;
        private String exerciseName;

        private Builder() {
        }

        public Builder withExerciseId(String exerciseIdToUse) {
            this.exerciseId = exerciseIdToUse;
            return this;
        }

        public Builder withRoutineId(String routineIdToUse) {
            this.routineId = routineIdToUse;
            return this;
        }

        public Builder withExerciseOrder(int exerciseOrderToUse) {
            this.exerciseOrder = exerciseOrderToUse;
            return this;
        }

        public Builder withExerciseName(String exerciseNameToUse) {
            this.exerciseName = exerciseNameToUse;
            return this;
        }

        public ExerciseModel build() {
            return new ExerciseModel(this);
        }
    }
}
