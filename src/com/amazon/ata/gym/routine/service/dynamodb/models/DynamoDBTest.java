package com.amazon.ata.gym.routine.service.dynamodb.models;

import com.amazon.ata.gym.routine.service.converters.ExerciseLinkedListConverter;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperTableModel;

import java.util.Arrays;
import java.util.List;

public class DynamoDBTest {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper mapper = new DynamoDBMapper(client);

        // Replace with your actual id value
        String id = "R008";

        try {
            Routine routine = mapper.load(Routine.class, id);
            System.out.println("Routine: " + routine);
        } catch (Exception e) {
            System.err.println("Unable to load item: " + e.getMessage());
        }
    }
   /* public static void main(String[] args) {*/
    /*    ExerciseLinkedListConverter converter = new ExerciseLinkedListConverter();

        // Sample list
        List<Exercise> exercises = Arrays.asList(
                new Exercise("ex001", 1, "Legs", "Running"),
                new Exercise("ex002", 2, "Full Body", "Jump Rope")
        );
        // Convert to JSON
        String json = converter.convert(exercises);
        System.out.println("JSON: " + json);

        // Unconvert from JSON
        List<Exercise> convertedBack = converter.unconvert(json);
        System.out.println("Converted back: " + convertedBack);*/

}
