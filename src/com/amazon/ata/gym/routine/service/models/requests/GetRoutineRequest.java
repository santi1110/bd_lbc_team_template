package com.amazon.ata.gym.routine.service.models.requests;

import java.util.Objects;

/**
 * Request class to get details of a specific gym routine by its ID.
 */
public class GetRoutineRequest {
    private String routineId; // Unique identifier for the gym routine

    public GetRoutineRequest() {
    }

    public GetRoutineRequest(Builder builder) {
        this.routineId = builder.routineId;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRoutineRequest that = (GetRoutineRequest) o;
        return Objects.equals(routineId, that.routineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routineId);
    }

    @Override
    public String toString() {
        return "GetRoutineRequest{" +
                "routineId='" + routineId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String routineId;

        private Builder() {
        }

        public Builder withRoutineId(String routineIdToUse) {
            this.routineId = routineIdToUse;
            return this;
        }

        public GetRoutineRequest build() {
            return new GetRoutineRequest(this);
        }
    }
}

