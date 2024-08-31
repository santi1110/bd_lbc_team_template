package com.amazon.ata.gym.routine.service.models.requests;

import java.util.List;
import java.util.Objects;

/**
 * Request class to create a new gym routine.
 */
public class CreateRoutineRequest {
    private String name; // Name of the gym routine
    private String customerId; // Customer ID associated with the routine
    private List<String> tags; // Tags associated with the routine

    public CreateRoutineRequest() {
    }

    public CreateRoutineRequest(String name, String customerId, List<String> tags) {
        this.name = name;
        this.customerId = customerId;
        this.tags = tags;
    }

    public CreateRoutineRequest(Builder builder) {
        this.name = builder.name;
        this.customerId = builder.customerId;
        this.tags = builder.tags;
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
        CreateRoutineRequest that = (CreateRoutineRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customerId, tags);
    }

    @Override
    public String toString() {
        return "CreateRoutineRequest{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +


