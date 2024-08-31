package com.amazon.ata.gym.routine.service.models.requests;

import java.util.Objects;

public class AddExerciseToRoutineRequest {
    private String routineId;
    private String equipmentId;
    private int setNumber;
    private boolean queueNext;

    public AddExerciseToRoutineRequest() {
    }

    public AddExerciseToRoutineRequest(String routineId, String equipmentId, int setNumber, boolean queueNext) {
        this.routineId = routineId;
        this.equipmentId = equipmentId;
        this.setNumber = setNumber;
        this.queueNext = queueNext;
    }

    public AddExerciseToRoutineRequest(Builder builder) {
        this.routineId = builder.routineId;
        this.equipmentId = builder.equipmentId;
        this.setNumber = builder.setNumber;
        this.queueNext = builder.queueNext;
    }

    public String getRoutineId() {
        return routineId;
    }

    public void setRoutineId(String routineId) {
        this.routineId = routineId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
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
        return setNumber == that.setNumber &&
                queueNext == that.queueNext &&
                Objects.equals(routineId, that.routineId) &&
                Objects.equals(equipmentId, that.eq

