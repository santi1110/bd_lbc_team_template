package com.amazon.ata.gym.routine.service.models.requests;

import java.util.Objects;

/**
 * Request class to get details of a specific gym routine by its ID.
 */
public class GetRoutineRequest {
    private String id; // Unique identifier for the gym routine

    public GetRoutineRequest() {
    }

    public GetRoutineRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRoutineRequest that = (GetRoutineRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetRoutineRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;

        private Builder() {
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public GetRoutineRequest build() {
            return new GetRoutineRequest(this);
        }
    }
}



