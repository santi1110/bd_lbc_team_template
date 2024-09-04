package com.amazon.ata.gym.routine.service.tct;

import com.amazon.ata.test.assertions.PlantUmlClassDiagramAssertions;
import com.amazon.ata.test.helper.AtaTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.amazon.ata.test.assertions.PlantUmlClassDiagramAssertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("MT1-Design")
public class MT1DesignClassDiagramIntrospectionTests {
    private static final String CLASS_DIAGRAM_PATH = "mastery-task1-gym-routine-CD.puml";

    private String content;

    @BeforeEach
    public void setup() {
        content = AtaTestHelper.getFileContentFromResources(CLASS_DIAGRAM_PATH);
    }

    @Test
    void mt1Design_getClassDiagram_nonEmptyFileExists() {
        assertFalse(content.trim().isEmpty(),
                String.format("Expected file: %s to contain class diagram but was empty", CLASS_DIAGRAM_PATH));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AddExerciseToRoutineActivity", "CreateRoutineActivity", "GetRoutineActivity",
            "GetRoutineExercisesActivity", "UpdateRoutineActivity", "Exercise", "Routine", "ExerciseDao",
            "RoutineDao", "ExerciseNotFoundException", "InvalidAttributeValueException",
            "RoutineNotFoundException"})
    void mt1Design_getClassDiagram_containsClasses(String packagingClass) {
        PlantUmlClassDiagramAssertions.assertClassDiagramContainsClass(content, packagingClass);
    }

    @ParameterizedTest
    @MethodSource("containsRelationshipProvider")
    void mt1Design_getClassDiagram_includesExpectedContainsRelationships(String containingType, String containedType) {
        assertClassDiagramIncludesContainsRelationship(content, containingType, containedType);
    }

    private static Stream<Arguments> containsRelationshipProvider() {
        return Stream.of(
                Arguments.of("Routine", "Exercise"),
                Arguments.of("AddExerciseToRoutineActivity", "ExerciseDao"),
                Arguments.of("CreateRoutineActivity", "RoutineDao"),
                Arguments.of("AddExerciseToRoutineActivity", "RoutineDao"),
                Arguments.of("GetRoutineActivity", "RoutineDao"),
                Arguments.of("GetRoutineExercisesActivity", "RoutineDao"),
                Arguments.of("UpdateRoutineActivity", "RoutineDao")
        );
    }

    @Test
    void mt1Design_getClassDiagram_containsExerciseFields() {
        assertClassDiagramTypeContainsMember(
                content, "Exercise", "@DynamoDBHashKey\\s*exerciseId\\s*:\\s*String", "exerciseId");
        assertClassDiagramTypeContainsMember(
                content, "Exercise", "@DynamoDBRangeKey\\s*exerciseNumber\\s*:\\s*Integer", "exerciseNumber");
        assertClassDiagramTypeContainsMember(
                content, "Exercise", "muscleGroupName\\s*:\\s*String", "muscleGroupName");
        assertClassDiagramTypeContainsMember(
                content, "Exercise", "exerciseName\\s*:\\s*String", "exerciseName");
    }

    @Test
    void mt1Design_getClassDiagram_containsRoutineFields() {
        assertClassDiagramTypeContainsMember(
                content, "Routine", "@DynamoDBHashKey\\s*id\\s*:\\s*String", "id");
        assertClassDiagramTypeContainsMember(
                content, "Routine", "name\\s*:\\s*String", "name");
        assertClassDiagramTypeContainsMember(
                content, "Routine", "customerId\\s*:\\s*String", "customerId");
        assertClassDiagramTypeContainsMember(
                content, "Routine", "exerciseCount\\s*:\\s*Integer", "exerciseCount");
        assertClassDiagramTypeContainsMember(
                content, "Routine", "tags\\s*:\\s*Set<String>", "tags");
        assertClassDiagramTypeContainsMember(
                content, "Routine", "exerciseList\\s*:\\s*List<Exercise>", "exerciseList");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ExerciseDao", "RoutineDao"})
    void mt1Design_getClassDiagram_daosContainDynamoDBMapper(String type) {
        System.out.println("Class Diagram Content:\n" + content);
        assertClassDiagramTypeContainsMember(
                content, type, "dynamoDbMapper\\s*:\\sDynamoDBMapper", "dynamoDbMapper");
    }

    @ParameterizedTest
    @ValueSource(strings = {"AddExerciseToRoutine", "CreateRoutine", "GetRoutine", "GetRoutineExercises", "UpdateRoutine"})
    void mt1Design_getClassDiagram_activitiesContainMethods(String name) {
        String type = name + "Activity";
        String returnType = name + "Result";
        List<String> arguments = Arrays.asList(name + "Request");
        assertClassDiagramTypeContainsMethod(content, type, "handleRequest", returnType, arguments);
    }

    @Test
    void mt1Design_getClassDiagram_routineDaoContainsMethod() {
        assertClassDiagramTypeContainsMethod(content, "RoutineDao", "getRoutine", "Routine",
                Arrays.asList("String"));
    }
}


