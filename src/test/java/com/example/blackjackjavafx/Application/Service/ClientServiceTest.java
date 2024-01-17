package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Application.testUtility;
import com.example.blackjackjavafx.Metier.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static ClientService clientService;

    private static String loginTest;
    private static String mailTest;

    @BeforeAll
    public static void setUp() {
        clientService = ClientService.getInstance();
        loginTest = testUtility.generateRandomLogin(10);
        mailTest = testUtility.generateRandomMail();
        testCreerClient();
    }



    public static void testCreerClient() {
        int initialSize = clientService.getClients().size();

        clientService.creerClient(loginTest, mailTest, "Doe", "John", 1000, "password",
                LocalDate.now());

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
        clientService.supprimeClient(retrievedClient.getLogin());

        int newSize = clientService.getClients().size();
        assertEquals(initialSize-1, newSize);
    }



}