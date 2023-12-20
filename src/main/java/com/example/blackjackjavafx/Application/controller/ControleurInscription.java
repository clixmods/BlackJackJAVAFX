package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.InscriptionHelper;
import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ControleurInscription
{
    //region FXML Variables
    public DatePicker inputDateNaissance;
    public PasswordField inputPassword2;
    public PasswordField inputPassword1;
    public TextField inputPseudo;
    public Label messageInfo;
    public Button submitInscription;
    public TextField inputPrenom;
    public TextField inputNom;
    public TextField inputMail;
    public TextField inputPhoneNumber;
    public TextField inputAddress;
    public TextField inputPostalCode;
    public TextField inputCity;
    public TextField inputCountry;
    //endregion
    private SceneHandler sceneHandler;
    public void initialiserInscription(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }

    public void OnMailEnter(ActionEvent actionEvent) {
    }

    public void OnNomEnter(ActionEvent actionEvent) {
    }

    public void OnPrenomEnter(ActionEvent actionEvent) {
    }

    public void OnPseudoEnter(ActionEvent actionEvent) {
    }

    public void OnPasswordEnter(ActionEvent actionEvent) {
    }

    public void OnConfirmPasswordEnter(ActionEvent actionEvent) {
    }

    public void OnDateEnter(ActionEvent actionEvent) {
    }

    public void OnSubmit(ActionEvent actionEvent)
    {
        // Get inputs text
        String login = inputPseudo.getText();
        String mail = inputMail.getText();
        String nom = inputNom.getText();
        String prenom = inputPrenom.getText();
        int argent = 0;
        String password = inputPassword1.getText();
        String passwordConfirm = inputPassword2.getText();
        //String adresse = inputAddress.getText();
        //int codePostal = 34000;
        //String ville = inputCity.getText();
        LocalDate dateNaissance = inputDateNaissance.getValue();
        //Date dateInscription = new Date();
        //String telephone = inputPhoneNumber.getText();

        if( StartInscription(login, mail, nom, prenom, argent, password, passwordConfirm, dateNaissance))
        {
            // TODO : Ouvrir un menu pour une connexion reussie
        }

    }

    public static Boolean StartInscription(String login,
                                           String mail,
                                           String nom,
                                           String prenom,
                                           int argent,
                                           String password,
                                           String passwordConfirm,
                                           LocalDate dateNaissance)
    {
        ClientService clientService = ClientService.getInstance();
        Password passwordService = new Password();

        if(!InscriptionHelper.isLoginAvailable(login))
        {
            System.out.println("login not available");
            return false;
        }

        if(!InscriptionHelper.isMailAvailable(mail))
        {
            System.out.println("Mail not available");
            return false;
        }
        if(!passwordService.isSecure(password))
        {
            System.out.println("Password is not secure");
            return false;
        }
        if(!password.equals(passwordConfirm))
        {
            System.out.println("Passwords is not equals");
            return false;
        }
        if(nom.isBlank() || prenom.isBlank())
        {
            System.out.println("Nom et prenom est vide");
            return false;
        }
        if(!InscriptionHelper.isMajor(dateNaissance))
        {
            System.out.println("Pas majeur");
            return false;
        }
        return clientService.creerClient(login, mail, nom, prenom, argent, password, dateNaissance);


    }
}
