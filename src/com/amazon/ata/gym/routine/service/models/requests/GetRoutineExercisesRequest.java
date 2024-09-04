package com.amazon.ata.gym.routine.service.models.requests;

import com.amazon.ata.gym.routine.service.models.ExerciseOrder; // Ensure this class is defined

import java.util.Objects;

/**
 * Request class to get exercises from a specific routine.
 */
public class GetRoutineExercisesRequest {
    private String routineId; // Unique identifier for the gym routine
    private ExerciseOrder order; // Order in which to return the exercises

    public GetRoutineExercisesRequest() {
    }

    public GetRoutineExercisesRequest(String routineId, ExerciseOrder order) {
        this.routineId = routineId;
        this.order = order;
    }

    public GetRoutineExercisesRequest(Builder builder) {
        this.routineId = builder.routineId;
        this.order = builder.order;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public ExerciseOrder getOrder() {
        return order;
    }

    public void setOrder(ExerciseOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRoutineExercisesRequest that = (GetRoutineExercisesRequest) o;
        return Objects.equals(routineId, that.routineId) &&
                order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineId, order);
    }

    @Override
    public String toString() {
        return "GetRoutineExercisesRequest{" +
                "routineId='" + routineId + '\'' +
                ", order=" + order +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String routineId;
        private ExerciseOrder order;

        private Builder() {
        }

        public Builder withRoutineId(String routineIdToUse) {
            this.routineId = routineIdToUse;
            return this;
        }

        public Builder withOrder(ExerciseOrder orderToUse) {
            this.order = orderToUse;
            return this;
        }

        public GetRoutineExercisesRequest build() {
            return new GetRoutineExercisesRequest(this);
        }
    }
}


