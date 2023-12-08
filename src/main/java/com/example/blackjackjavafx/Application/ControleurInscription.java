package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.Date;

public class ControleurInscription
{
    public DatePicker inputDateNaissance;
    public PasswordField inputPassword2;
    public PasswordField inputPassword1;
    public TextField inputPseudo;
    public Label messageInfo;
    public Button submitInscription;
    public TextField inputPrenom;
    public TextField inputNom;
    public TextField inputMail;
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
        String login = inputPseudo.getText();
        String mail = inputMail.getText();
        String nom = inputNom.getText();
        String prenom = inputPrenom.getText();
        int argent = 5000;
        String password = inputPassword1.getText();
        String adresse = null;
        int codePostal = 0;
        String ville = null;
        Date dateNaissance = null;
        Date dateInscription = null;
        String telephone = null;

        ClientService clientService = ClientService.getInstance();
        clientService.creerClient(-1,login, mail, nom, prenom, argent,password
        ,adresse, codePostal, ville, dateNaissance, dateInscription,telephone);
    }
}
