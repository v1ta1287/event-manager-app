package com.example.eventmanagerapp.controller.util;

import java.util.Random;

public class IdGeneratorUtility {
    // generate random category id based on assignment specs
    public static String generateCategoryId() {
        // Build categoryId, starting off with appending C
        StringBuilder categoryId = new StringBuilder();
        categoryId.append("C");

        // Append two random characters
        Random random = new Random();
        char randomizedCharacter;
        for (int i = 0; i < 2; i++){
            randomizedCharacter = (char) (random.nextInt(26) + 'a');
            categoryId.append(Character.toUpperCase(randomizedCharacter));
        }

        // Append hyphen
        categoryId.append("-");

        // Append four random digits
        for (int i = 0; i < 4; i++){
            categoryId.append(random.nextInt(10));
        }

        return categoryId.toString();
    }

    // generates a random event ID based on assignment specs
    public static String generateEventId() {
        // Build eventId, starting off with appending E
        StringBuilder eventId = new StringBuilder();
        eventId.append("E");

        // Append two random characters
        Random random = new Random();
        char randomizedCharacter;
        for (int i = 0; i < 2; i++){
            randomizedCharacter = (char) (random.nextInt(26) + 'a');
            eventId.append(Character.toUpperCase(randomizedCharacter));
        }

        // Append hyphen
        eventId.append("-");

        // Append five random digits
        for (int i = 0; i < 5; i++){
            eventId.append(random.nextInt(10));
        }

        return eventId.toString();
    }
}
