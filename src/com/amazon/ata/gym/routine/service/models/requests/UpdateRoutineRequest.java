package com.amazon.ata.gym.routine.service.models.requests;

import java.util.Objects;
import java.util.Set;

/**
 * Request class to update a gym routine.
 */
public class UpdateRoutineRequest {
    private String id; // Unique identifier for the gym routine
    private String name; // Name of the routine
    private String customerId; // Customer ID associated with the routine
    private Set<String> tags; // Tags associated with the routine

    public UpdateRoutineRequest() {
    }

    public UpdateRoutineRequest(String id, String name, String customerId, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.tags = tags;
    }

    public UpdateRoutineRequest(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.customerId = builder.customerId;
        this.tags = builder.tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRoutineRequest that = (UpdateRoutineRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, customerId, tags);
    }

    @Override
    public String toString() {
        return "UpdateRoutineRequest{" +
                "routineId='" + id + '\'' +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", tags=" + tags +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private String customerId;
        private Set<String> tags;

        private Builder() {
        }

        public Builder withRoutineId(String idToUse){
            this.id = idToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withCustomerId(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }

        public Builder withTags(Set<String> tagsToUse) {
            this.tags = tagsToUse;
            return this;
        }

        public UpdateRoutineRequest build() {
            return new UpdateRoutineRequest(this);
        }
    }
}
