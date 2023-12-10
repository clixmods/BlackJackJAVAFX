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

    @Test
    public void testHashAndVerify() {
        String clearPassword = "testPassword";
        String hashedPassword = Password.hash(clearPassword);

        assertNotNull(hashedPassword);

        boolean isValid = Password.verify(clearPassword, hashedPassword);

        assertTrue(isValid);
    }
    @Test
    public void testVerifyInvalidPassword() {
        String clearPassword = "testPassword";
        String hashedPassword = Password.hash(clearPassword);

        String wrongPassword = "wrongPassword";
        assertFalse(Password.verify(wrongPassword, hashedPassword));
    }

    @Test
    public void testVerifyDifferentPassword() {
        String clearPassword1 = "testPassword";
        String hashedPassword1 = Password.hash(clearPassword1);

        String clearPassword2 = "anotherPassword";
        String hashedPassword2 = Password.hash(clearPassword2);

        assertFalse(Password.verify(clearPassword1, hashedPassword2));
    }


    @Test
    public void testHashSamePasswordConsistency() {
        String clearPassword = "testPassword";
        String hashedPassword1 = Password.hash(clearPassword);
        String hashedPassword2 = Password.hash(clearPassword);

        assertEquals(hashedPassword1, hashedPassword2);
    }

}
