package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.InscriptionHelper;
import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.Date;

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
        int argent = 5000;
        String password = inputPassword1.getText();
        String passwordConfirm = inputPassword2.getText();
        String adresse = inputAddress.getText();
        int codePostal = 34000;
        String ville = inputCity.getText();
        Date dateNaissance = new Date();
        Date dateInscription = new Date();
        String telephone = "0467834609";

        ClientService clientService = ClientService.getInstance();
        Password passwordService = new Password();

        if(InscriptionHelper.IsLoginAvailable(login)
        && InscriptionHelper.IsMailAvailable(mail)
        && passwordService.IsSecure(password)
        && password.equals(passwordConfirm)
        && !nom.isBlank()
        && !prenom.isBlank()
        && !adresse.isBlank()
        && codePostal > 0
        && !ville.isBlank()
        && !telephone.isBlank())
        {
            clientService.creerClient(-1,login, mail, nom, prenom, argent,password
                    ,adresse, codePostal, ville, dateNaissance, dateInscription,telephone);
        }
        else
        {
            System.out.println("Echec inscription");
        }



    }
}
