package com.example.blackjackjavafx.Application.helper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CityCheckerTest {
    @Test
    public void testExistCity() {
        CityChecker cityChecker = new CityChecker();
        assertTrue(cityChecker.IsValidCity("Jacou"));
    }
    @Test
    public void testCityDoesntExist() {
        CityChecker cityChecker = new CityChecker();
        assertFalse(cityChecker.IsValidCity("dgdhfghfgjhfhfj"));
    }
}