package com.amazon.ata.gym.routine.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the exercises table.
 */
@DynamoDBTable(tableName = "exercises")
public class Exercise {
    private String exerciseId; // Unique identifier for the exercise
    private Integer exerciseNumber; // Order or identifier for the exercise in a routine
    private String muscleGroupName; // Name of the muscle group (formerly albumName)
    private String exerciseName; // Name of the exercise (formerly songTitle)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise that = (Exercise) o;
        return Objects.equals(exerciseId, that.exerciseId) &&
                Objects.equals(exerciseNumber, that.exerciseNumber) &&
                Objects.equals(muscleGroupName, that.muscleGroupName) &&
                Objects.equals(exerciseName, that.exerciseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, exerciseNumber, muscleGroupName, exerciseName);
    }

    @DynamoDBHashKey(attributeName = "exercise_id")
    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    @DynamoDBRangeKey(attributeName = "exercise_number")
    public Integer getExerciseNumber() {
        return exerciseNumber;
    }

    public void setExerciseNumber(Integer exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }

    @DynamoDBAttribute(attributeName = "muscle_group_name")
    public String getMuscleGroupName() {
        return muscleGroupName;
    }

    public void setMuscleGroupName(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

    @DynamoDBAttribute(attributeName = "exercise_name")
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}


