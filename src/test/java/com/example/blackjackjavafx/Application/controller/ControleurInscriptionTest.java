package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControleurInscriptionTest {

    @Test
    void testInscriptionClient() {
        // Créez une instance de votre classe ou utilisez une instance existante si possible
        ControleurInscription votreClasse = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        // Création de fausses valeurs pour le test
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD!";
        String passwordConfirm = "1passworD!";
        String adresse = "123 Rue Example";
        int codePostal = 34000;
        String ville = "Montpellier";
        Date dateNaissance = new Date(98, Calendar.JUNE,13);
        Date dateInscription = new Date();
        String telephone = "1234567890";

        Boolean result = votreClasse.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
                adresse, codePostal, ville, dateNaissance, dateInscription, telephone);
        assertTrue(result);
        Client clientCreated = clientService.getClient("pseudo");
        assertNotNull(clientCreated);

        clientService.supprimeClient(clientCreated.getId());

    }

    @Test
    void testInscriptionClientWithStrangeCaseOnTheCityName() {
        // Créez une instance de votre classe ou utilisez une instance existante si possible
        ControleurInscription votreClasse = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        // Création de fausses valeurs pour le test
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD!";
        String passwordConfirm = "1passworD!";
        String adresse = "123 Rue Example";
        int codePostal = 34000;
        String ville = "MonTpeLlIeR";
        Date dateNaissance = new Date(98, Calendar.JUNE,13);
        Date dateInscription = new Date();
        String telephone = "1234567890";

        Boolean result = votreClasse.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
                adresse, codePostal, ville, dateNaissance, dateInscription, telephone);
        assertTrue(result);
        Client clientCreated = clientService.getClient("pseudo");
        assertNotNull(clientCreated);

        clientService.supprimeClient(clientCreated.getId());

    }

    @Test
    void testInscriptionClientMotDePasseInvalid() {
        // Créez une instance de votre classe ou utilisez une instance existante si possible
        ControleurInscription votreClasse = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        // Création de fausses valeurs pour le test
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "!";
        String passwordConfirm = "1passworD!";
        String adresse = "123 Rue Example";
        int codePostal = 34000;
        String ville = "Montpellier";
        Date dateNaissance = new Date();
        Date dateInscription = new Date();
        String telephone = "1234567890";

        Boolean result = votreClasse.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
                adresse, codePostal, ville, dateNaissance, dateInscription, telephone);
        assertFalse(result);

    }

    @Test
    void testInscriptionClientMotDePassePasConfirmer() {
        // Créez une instance de votre classe ou utilisez une instance existante si possible
        ControleurInscription votreClasse = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        // Création de fausses valeurs pour le test
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD";
        String passwordConfirm = "1passworD!!";
        String adresse = "123 Rue Example";
        int codePostal = 34000;
        String ville = "Montpellier";
        Date dateNaissance = new Date();
        Date dateInscription = new Date();
        String telephone = "1234567890";

        Boolean result = votreClasse.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
                adresse, codePostal, ville, dateNaissance, dateInscription, telephone);
        assertFalse(result);

    }

    @Test
    void testInscriptionClientVilleIntrouvable() {
        // Créez une instance de votre classe ou utilisez une instance existante si possible
        ControleurInscription votreClasse = new ControleurInscription();
        ClientService clientService = ClientService.getInstance();
        // Création de fausses valeurs pour le test
        String login = "pseudo";
        String mail = "test@mail.com";
        String nom = "Nom";
        String prenom = "Prenom";
        int argent = 5000;
        String password = "1passworD";
        String passwordConfirm = "1passworD!!";
        String adresse = "123 Rue Example";
        int codePostal = 34000;
        String ville = "Skyrim Bordeciel";
        Date dateNaissance = new Date();
        Date dateInscription = new Date();
        String telephone = "1234567890";

        Boolean result = votreClasse.StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm,
                adresse, codePostal, ville, dateNaissance, dateInscription, telephone);
        assertFalse(result);

    }


}