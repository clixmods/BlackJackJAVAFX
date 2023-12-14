package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.testUtility;
import com.example.blackjackjavafx.Metier.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InscriptionHelperTest {

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

        clientService.creerClient(1, loginTest, mailTest, "Doe", "John", 1000, "password",
                 new Date());

        int newSize = clientService.getClients().size();

        assertEquals(initialSize + 1, newSize);
    }
    @Test
    void isLoginNotAvailable() {
        assertFalse(InscriptionHelper.isLoginAvailable(loginTest));
    }

    @Test
    void isMailNotAvailable() {
        assertFalse(InscriptionHelper.isMailAvailable(mailTest));

    }

    @AfterAll
    public static void cleanUp() {
        Client client = clientService.getClient(loginTest);
        clientService.supprimeClient(client.getId());
    }


}