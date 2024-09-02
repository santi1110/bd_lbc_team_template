package com.amazon.ata.gym.routine.service.models.requests;

import java.util.Objects;

public class AddExerciseToRoutineRequest {
    private String routineId;
    private String exerciseId;
    private int exerciseNumber;
    private boolean queueNext;

    public AddExerciseToRoutineRequest() {
    }

    public AddExerciseToRoutineRequest(String routineId, String exerciseId, int exerciseNumber, boolean queueNext) {
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.exerciseNumber = exerciseNumber;
        this.queueNext = queueNext;
    }

    public AddExerciseToRoutineRequest(Builder builder) {
        this.routineId = builder.routineId;
        this.exerciseId = builder.exerciseId;
        this.exerciseNumber = builder.exerciseNumber;
        this.queueNext = builder.queueNext;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getExerciseNumber() {
        return exerciseNumber;
    }

    public void setExerciseNumber(int exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }

    public boolean isQueueNext() {
        return queueNext;
    }

    public void setQueueNext(boolean queueNext) {
        this.queueNext = queueNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddExerciseToRoutineRequest that = (AddExerciseToRoutineRequest) o;
        return exerciseNumber == that.exerciseNumber &&
                queueNext == that.queueNext &&
                Objects.equals(routineId, that.routineId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineId, exerciseId, exerciseNumber, queueNext);
    }

    @Override
    public String toString() {
        return "AddExerciseToRoutineRequest{" +
                "routineId='" + routineId + '\'' +
                ", exerciseId='" + exerciseId + '\'' +
                ", exerciseNumber=" + exerciseNumber +
                ", queueNext=" + queueNext +
                '}';
    }

    public static class Builder {
        private String routineId;
        private String exerciseId;
        private int exerciseNumber;
        private boolean queueNext;

        public Builder withRoutineId(String routineId) {
            this.routineId = routineId;
            return this;
        }

        public Builder withExerciseId(String exerciseId) {
            this.exerciseId = exerciseId;
            return this;
        }

        public Builder withExerciseNumber(int exerciseNumber) {
            this.exerciseNumber = exerciseNumber;
            return this;
        }

        public Builder withQueueNext(boolean queueNext) {
            this.queueNext = queueNext;
            return this;
        }

        public AddExerciseToRoutineRequest build() {
            return new AddExerciseToRoutineRequest(this);
        }
    }
}


