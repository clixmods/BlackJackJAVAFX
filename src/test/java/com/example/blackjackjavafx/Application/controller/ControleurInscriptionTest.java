package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ControleurInscriptionTest {

    @Test
    void testInscriptionClient() {
        ControleurInscription controleur = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD!";
        String passwordConfirm = "1passworD!";
        LocalDate dateNaissance = LocalDate.of(98, Calendar.JUNE, 13);

        Boolean result = controleur.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
              dateNaissance);
        assertTrue(result);
        Client clientCreated = clientService.getClient("pseudo");
        assertNotNull(clientCreated);

        clientService.supprimeClient(clientCreated.getLogin());
    }

    @Test
    void testInscriptionClientMotDePasseInvalid() {
        ControleurInscription controleur = new ControleurInscription();
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "!";
        String passwordConfirm = "1passworD!";
        LocalDate dateNaissance = LocalDate.now();

        Boolean result = controleur.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
               dateNaissance);
        assertFalse(result);
    }

    @Test
    void testInscriptionClientMotDePassePasConfirmer() {
        ControleurInscription controleur = new ControleurInscription();
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD";
        String passwordConfirm = "1passworD!!";
        LocalDate dateNaissance = LocalDate.now();

        Boolean result = controleur.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
             dateNaissance);
        assertFalse(result);
    }
}
