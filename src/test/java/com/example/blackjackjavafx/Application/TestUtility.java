package com.example.blackjackjavafx.Application;

import java.util.Random;

public class TestUtility {
    public static final String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "example.com", "domain.com"};
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomMail() {
        String randomLogin = generateRandomLogin(8);
        String randomDomain = getRandomDomain();

        return randomLogin + "@" + randomDomain;
    }

    private static String getRandomDomain() {
        Random random = new Random();
        int randomIndex = random.nextInt(DOMAINS.length);
        return DOMAINS[randomIndex];
    }

    public static String generateRandomLogin(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder generatedLogin = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            generatedLogin.append(randomChar);
        }

        return generatedLogin.toString();
    }
}
