package com.amazon.ata.gym.routine.service.dynamodb;

import com.amazon.ata.gym.routine.service.dynamodb.models.Routine; // Updated model import
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException; // Updated exception import
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

/**
 * Accesses data for a routine using {@link Routine} to represent the model in DynamoDB.
 */
public class RoutineDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a RoutineDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the routines table
     */
    @Inject
    public RoutineDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link Routine} corresponding to the specified id.
     *
     * @param id the Routine ID
     * @return the stored Routine, or null if none was found.
     */
    public Routine getRoutine(String id) {
        Routine routine = this.dynamoDbMapper.load(Routine.class, id);

        if (routine == null) {
            throw new RoutineNotFoundException("Could not find routine with id " + id); // Updated exception
        }

        return routine;
    }

    /**
     * Saves the provided {@link Routine} to DynamoDB.
     *
     * @param routine the Routine to save
     */
    public void saveRoutine(Routine routine) {
        dynamoDbMapper.save(routine);
    }
}

