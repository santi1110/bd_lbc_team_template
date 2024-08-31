package com.amazon.ata.gym.routine.service.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public final class GymRoutineServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\]");
    // package private for testing
    static final int ROUTINE_ID_LENGTH = 5; // Renamed to ROUTINE_ID_LENGTH

    // do not instantiate
    private GymRoutineServiceUtils() {}

    /**
     * Checks that the provided String contains only valid characters.
     *
     * @param stringToValidate the routine name to be validated
     * @return true if the String is valid (contains only valid characters),
     *         false otherwise
     */
    public static boolean isValidString(final String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        }

        return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }

    public static String generateRoutineId() {
        return RandomStringUtils.randomAlphanumeric(ROUTINE_ID_LENGTH); // Renamed method
    }
}

