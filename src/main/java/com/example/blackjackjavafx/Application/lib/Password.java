package com.example.blackjackjavafx.Application.lib;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    private static final String ALGORITHM = "HmacSHA256";
    private static final String PEPPER = "y7kKvgjK+3H6oGleeqYOUn";


    public static String hash(String clearPassword) {
        try {
            // Génération de HMAC avec SHA-256
            Mac sha256Hmac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(PEPPER.getBytes(), ALGORITHM);
            sha256Hmac.init(secretKey);
            byte[] hmacBytes = sha256Hmac.doFinal(clearPassword.getBytes());

            // Convertir le résultat HMAC en une chaîne hexadécimale
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hmacBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            String hashedPepper = hexStringBuilder.toString();

            // Utilisation de l'algorithme de hachage SHA-256 pour le résultat HMAC
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha256Digest.digest(hashedPepper.getBytes());

            // Convertir le résultat final en une chaîne hexadécimale
            StringBuilder resultBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                resultBuilder.append(String.format("%02x", b));
            }

            return resultBuilder.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean verify(String clearPassword, String hashedPassword) {
        try {
            // Génération de HMAC avec SHA-256
            Mac sha256Hmac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(PEPPER.getBytes(), ALGORITHM);
            sha256Hmac.init(secretKey);
            byte[] hmacBytes = sha256Hmac.doFinal(clearPassword.getBytes());

            // Convertir le résultat HMAC en une chaîne hexadécimale
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hmacBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            String hashedPepper = hexStringBuilder.toString();

            // Utilisation de l'algorithme de hachage SHA-256 pour le résultat HMAC
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha256Digest.digest(hashedPepper.getBytes());

            // Convertir le résultat final en une chaîne hexadécimale
            StringBuilder resultBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                resultBuilder.append(String.format("%02x", b));
            }

            // Comparer les résultats pour la vérification
            return hashedPassword.equals(resultBuilder.toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
    }

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
