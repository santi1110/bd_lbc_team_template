package com.amazon.ata.gym.routine.service.models;

import java.util.List;
import java.util.Objects;

/**
 * Model class representing a gym routine.
 */
public class RoutineModel {
    private String id;
    private String name;
    private String customerId;
    private int exerciseCount; // Changed from songCount to exerciseCount
    private List<String> tags;

    public RoutineModel() {

    }

    public RoutineModel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.customerId = builder.customerId;
        this.exerciseCount = builder.exerciseCount; // Updated field
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

    public int getExerciseCount() { // Updated method name
        return exerciseCount;
    }

    public void setExerciseCount(int exerciseCount) { // Updated method name
        this.exerciseCount = exerciseCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineModel that = (RoutineModel) o;
        return exerciseCount == that.exerciseCount && // Updated field
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, customerId, exerciseCount, tags); // Updated field
    }

    @Override
    public String toString() {
        return "RoutineModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", exerciseCount=" + exerciseCount + // Updated field
                ", tags=" + tags +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String name;
        private String customerId;
        private int exerciseCount; // Updated field
        private List<String> tags;

        public Builder withId(String idToUse) {
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

        public Builder withExerciseCount(int exerciseCountToUse) { // Updated method
            this.exerciseCount = exerciseCountToUse;
            return this;
        }

        public Builder withTags(List<String> tagsToUse) {
            this.tags = tagsToUse;
            return this;
        }

        public RoutineModel build() {
            return new RoutineModel(this);
        }
    }
}

