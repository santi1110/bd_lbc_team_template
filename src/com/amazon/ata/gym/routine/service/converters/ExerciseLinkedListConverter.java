package com.amazon.ata.gym.routine.service.converters;

import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise; // Updated model import
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Converter class for converting between a LinkedList of Exercise and its JSON representation.
 * Implements DynamoDBTypeConverter for DynamoDB integration.
 */
public class ExerciseLinkedListConverter implements DynamoDBTypeConverter<String, List<Exercise>> {
    private static final Gson GSON = new Gson();
    private static final Type EXERCISE_LIST_TYPE = new TypeToken<LinkedList<Exercise>>() {}.getType();
    private final Logger log = LogManager.getLogger();

    /**
     * Converts a List of Exercise objects to its JSON representation.
     *
     * @param listToBeConverted The list of Exercise objects to convert.
     * @return The JSON representation of the list.
     */
    @Override
    public String convert(List<Exercise> listToBeConverted) {
        try {
            return GSON.toJson(listToBeConverted);
        } catch (Exception e) {
            log.error("Error converting Exercise list to JSON", e);
            throw new RuntimeException("Conversion error", e);
        }
    }

    /**
     * Converts a JSON representation of Exercise objects back to a List.
     *
     * @param dynamoDbRepresentation The JSON representation of the list.
     * @return The List of Exercise objects.
     */
    @Override
    public List<Exercise> unconvert(String dynamoDbRepresentation) {
        try {
            return GSON.fromJson(dynamoDbRepresentation, EXERCISE_LIST_TYPE);
        } catch (JsonSyntaxException e) {
            log.error("Error converting JSON to Exercise list", e);
            throw new RuntimeException("Conversion error", e);
        }
    }
}

