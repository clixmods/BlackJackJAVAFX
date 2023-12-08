package com.example.blackjackjavafx.Application.lib;

public class Password {
    public boolean isSecure(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Vérifie la longueur minimale
        if (password.length() < 8) {
            return false;
        }

        // Vérifie la présence de caractères spéciaux
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        // Vérifie la présence de chiffres
        if (!containsDigit(password)) {
            return false;
        }

        // Vérifie la présence de lettres majuscules
        if (!containsUpperCaseLetter(password)) {
            return false;
        }

        // Le mot de passe satisfait tous les critères de sécurité
        return true;
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    private boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsUpperCaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }
}
