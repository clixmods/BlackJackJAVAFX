package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Metier.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static ClientService clientService;

    private static String loginTest;
    private static String mailTest;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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

    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "example.com", "domain.com"};

    public static String generateRandomMail() {
        String randomLogin = generateRandomLogin(8); // Utilisez la méthode précédente pour générer un login aléatoire
        String randomDomain = getRandomDomain();

        return randomLogin + "@" + randomDomain;
    }

    private static String getRandomDomain() {
        Random random = new Random();
        int randomIndex = random.nextInt(DOMAINS.length);
        return DOMAINS[randomIndex];
    }
    @BeforeAll
    public static void setUp() {
        clientService = ClientService.getInstance();
        loginTest = generateRandomLogin(10);
        mailTest = generateRandomMail();
        testCreerClient();
    }



    public static void testCreerClient() {
        int initialSize = clientService.getClients().size();

        clientService.creerClient(1, loginTest, mailTest, "Doe", "John", 1000, "password",
                "123 Main St", 12345, "City", new Date(), new Date(), "123456789");

        int newSize = clientService.getClients().size();

        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void testGetClientById() {
        clientService = ClientService.getInstance();
        Client retrievedClient = clientService.getClient(loginTest);
        assertNotNull(retrievedClient);
    }

    @Test
    public void testUpdateClientByLogin() {
        clientService = ClientService.getInstance();
        Client retrievedClient = clientService.getClient(loginTest);

        assertNotNull(retrievedClient);

        retrievedClient.setPrenom("Johnny");

        clientService.mettreAJourClient(retrievedClient);

        assertEquals(retrievedClient.getPrenom(), "Johnny");
    }

    @Test
    public void testSupprimerClient() {
        clientService = ClientService.getInstance();
        int initialSize = clientService.getClients().size();
        Client retrievedClient = clientService.getClient(loginTest);
        clientService.supprimeClient(retrievedClient.getId());

        int newSize = clientService.getClients().size();
        assertEquals(initialSize-1, newSize);
    }



}