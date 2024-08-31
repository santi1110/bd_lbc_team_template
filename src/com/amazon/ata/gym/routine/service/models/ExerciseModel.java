package com.amazon.ata.gym.routine.service.models;

/**
 * Model class representing an exercise.
 */
public class ExerciseModel {
    private String id;
    private String routine;
    private int orderNumber; // Changed from trackNumber to orderNumber
    private String name; // Changed from title to name

    public ExerciseModel() {

    }

    public ExerciseModel(Builder builder) {
        this.id = builder.id;
        this.routine = builder.routine;
        this.orderNumber = builder.orderNumber;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public int getOrderNumber() { // Updated method name
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) { // Updated method name

