package com.amazon.ata.gym.routine.service.tct;

import com.amazon.ata.test.assertions.PlantUmlSequenceDiagramAssertions;
import com.amazon.ata.test.helper.AtaTestHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("MT1-Design")
public class MT1DesignSequenceDiagramIntrospectionTests {
    private static final String GET_ROUTINE_SEQUENCE_DIAGRAM_PATH = "mastery-task1-get-routine-SD.puml";
    private static final String CREATE_ROUTINE_SEQUENCE_DIAGRAM_PATH =
            "mastery-task1-create-routine-SD.puml";

    @ParameterizedTest
    @ValueSource(strings = {"GetRoutineActivity", "RoutineDao"})
    void mt1Design_GetRoutineSequenceDiagram_includesExpectedTypes(String type) {
        String content = AtaTestHelper.getFileContentFromResources(GET_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsEntity(content, type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"RoutineNotFoundException", "Routine"})
    void mt1Design_GetRoutineSequenceDiagram_includesExpectedReturnTypes(String type) {
        String content = AtaTestHelper.getFileContentFromResources(GET_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsReturnType(content, type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getRoutine"})
    void mt1Design_GetRoutineSequenceDiagram_includesExpectedMethodCalls(String method) {
        String content = AtaTestHelper.getFileContentFromResources(GET_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsMethod(content, method);
    }

    @ParameterizedTest
    @ValueSource(strings = {"CreateRoutineActivity", "GymRoutineServiceUtils", "RoutineDao"})
    void mt1Design_CreateRoutineSequenceDiagram_includesExpectedTypes(String type) {
        String content = AtaTestHelper.getFileContentFromResources(CREATE_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsEntity(content, type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidAttributeValueException", "String"})
    void mt1Design_CreateRoutineSequenceDiagram_includesExpectedReturnTypes(String type) {
        String content = AtaTestHelper.getFileContentFromResources(CREATE_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsReturnType(content, type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"generateRoutineId", "saveRoutine"})
    void mt1Design_CreateRoutineSequenceDiagram_includesExpectedMethodCalls(String method) {
        String content = AtaTestHelper.getFileContentFromResources(CREATE_ROUTINE_SEQUENCE_DIAGRAM_PATH);

        PlantUmlSequenceDiagramAssertions.assertSequenceDiagramContainsMethod(
                content.toLowerCase(), method.toLowerCase());
    }
}

