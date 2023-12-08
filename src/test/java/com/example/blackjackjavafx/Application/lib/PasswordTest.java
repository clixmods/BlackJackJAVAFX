package com.example.blackjackjavafx.Application.lib;

import com.example.blackjackjavafx.Application.lib.Password;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordTest {

    @Test
    public void testSecurePassword() {
        Password passwordChecker = new Password();
        assertTrue(passwordChecker.isSecure("SecurePass123!"));
    }

    @Test
    public void testWeakPassword_Length() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure("Weak12")); // Longueur insuffisante
    }

    @Test
    public void testWeakPassword_NoSpecialChar() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure("WeakPassword123")); // Pas de caractère spécial
    }

    @Test
    public void testWeakPassword_NoDigit() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure("WeakPassword!")); // Pas de chiffre
    }

    @Test
    public void testWeakPassword_NoUpperCase() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure("weakpassword123!")); // Pas de lettre majuscule
    }

    @Test
    public void testEmptyPassword() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure("")); // Mot de passe vide
    }

    @Test
    public void testNullPassword() {
        Password passwordChecker = new Password();
        assertFalse(passwordChecker.isSecure(null)); // Mot de passe nul
    }
}
