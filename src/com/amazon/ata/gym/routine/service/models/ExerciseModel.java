package com.amazon.ata.gym.routine.service.models;

import java.util.Objects;

/**
 * Model class representing an exercise.
 */
public class ExerciseModel {
    private String exerciseId;
    private String muscleGroupName;
    private int exerciseNumber;
    private String exerciseName;

    public ExerciseModel() {
    }

    private ExerciseModel(Builder builder) {
        this.exerciseId = builder.exerciseId;
        this.muscleGroupName = builder.muscleGroupName;
        this.exerciseNumber = builder.exerciseOrder;
        this.exerciseName = builder.exerciseName;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getMuscleGroupName() {
        return muscleGroupName;
    }

    public void setMuscleGroupName(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

    public int getExerciseNumber() {
        return exerciseNumber;
    }

    public void setExerciseNumber(int exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
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
        return exerciseNumber == that.exerciseNumber &&
                Objects.equals(exerciseId, that.exerciseId) &&
                Objects.equals(muscleGroupName, that.muscleGroupName) &&
                Objects.equals(exerciseName, that.exerciseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, muscleGroupName, exerciseNumber, exerciseName);
    }

    @Override
    public String toString() {
        return "ExerciseModel{" +
                "exerciseId='" + exerciseId + '\'' +
                ", muscleGroupName='" + muscleGroupName + '\'' +
                ", exerciseOrder=" + exerciseNumber +
                ", exerciseName='" + exerciseName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String exerciseId;
        private String muscleGroupName;
        private int exerciseOrder;
        private String exerciseName;

        private Builder() {
        }

        public Builder withExerciseId(String exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }

        public Builder withMuscleGroupName(String muscleGroupName) {
            this.muscleGroupName = muscleGroupName;
            return this;
        }

        public Builder withExerciseOrder(int exerciseOrder) {
            this.exerciseOrder = exerciseOrder;
            return this;
        }

        public Builder withExerciseName(String exerciseName) {
            this.exerciseName = exerciseName;
            return this;
        }

        public ExerciseModel build() {
            return new ExerciseModel(this);
        }
    }
}
