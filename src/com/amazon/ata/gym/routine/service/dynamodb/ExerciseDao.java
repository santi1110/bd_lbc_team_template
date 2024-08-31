package com.amazon.ata.gym.routine.service.dynamodb;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise; // Updated model import
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

/**
 * Accesses data for an exercise using {@link Exercise} to represent the model in DynamoDB.
 */
public class ExerciseDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an ExerciseDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the exercises table
     */
    @Inject
    public ExerciseDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves an exercise from the DynamoDB table using its ID.
     *
     * @param id the ID of the exercise
     * @return the {@link Exercise} object
     */
    public Exercise getExercise(String id) {
        return dynamoDbMapper.load(Exercise.class, id);
    }

    // You might want to add other CRUD operations as needed, e.g., save, delete, etc.
}

