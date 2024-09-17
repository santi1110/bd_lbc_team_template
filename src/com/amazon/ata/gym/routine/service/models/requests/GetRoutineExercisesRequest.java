package com.amazon.ata.gym.routine.service.models.requests;

import com.amazon.ata.gym.routine.service.models.ExerciseOrder; // Ensure this class is defined

import java.util.Objects;

/**
 * Request class to get exercises from a specific routine.
 */
public class GetRoutineExercisesRequest {
    private String id; // Unique identifier for the gym routine
    private ExerciseOrder order; // Order in which to return the exercises

    public GetRoutineExercisesRequest() {
    }

    public GetRoutineExercisesRequest(String id, ExerciseOrder order) {
        this.id = id;
        this.order = order;
    }

    public GetRoutineExercisesRequest(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return Objects.equals(id, that.id) &&
                order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public String toString() {
        return "GetRoutineExercisesRequest{" +
                "routineId='" + id + '\'' +
                ", order=" + order +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private ExerciseOrder order;

        private Builder() {
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
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



