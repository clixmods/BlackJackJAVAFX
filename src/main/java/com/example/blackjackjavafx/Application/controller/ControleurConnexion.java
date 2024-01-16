package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Application.connection.ConnexionResult;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleurConnexion {

    @FXML
    private TextField inputLogin;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button buttonConnexion;

    @FXML
    private Label textInfo;

    @FXML
    private Label passwordFieldDescriptor;

    @FXML
    private Label loginFieldDescriptor;

    private SceneHandler sceneHandler;

    public void initialiserConnexion(SceneHandler sceneHandler) {
        afficherTexte();
        this.sceneHandler = sceneHandler;
    }

    private void afficherTexte(){
        loginFieldDescriptor.setText(LangageManager.getInstance().getText("connexion_loginFieldDescriptor"));
        passwordFieldDescriptor.setText(LangageManager.getInstance().getText("connexion_passwordFieldDescriptor"));
        textInfo.setText(LangageManager.getInstance().getText("connexion_textInfo_tryConnect"));
        buttonConnexion.setText(LangageManager.getInstance().getText("connexion_connexionButton"));
    }

    public void OnInputLoginEnter(ActionEvent actionEvent) {
    }

    public void OnInputPasswordEnter(ActionEvent actionEvent) {
    }

    public void OnConnexionSubmit(ActionEvent actionEvent) 
    {
        String login = inputLogin.getText();
        String password = inputPassword.getText();
        Connexion connexion = Connexion.getInstance();
        ConnexionResult result = connexion.tryConnect(login,password);
        switch (result) {
            case SUCCESS -> {
                textInfo.setText(LangageManager.getInstance().getText("connexion_textInfo_successfulConnection"));
                sceneHandler.mettreAJourHeader();
                // TODO : quest ce qu'on fait ?
            }
            case BAD_PASSWORD -> {
                textInfo.setText(LangageManager.getInstance().getText("connexion_textInfo_incorrectPassword"));
            }
            case UNDEFINED_LOGIN -> {
                textInfo.setText(LangageManager.getInstance().getText("connexion_textInfo_missingLogin"));
            }
            case UNKNOWN_ERROR -> {
                textInfo.setText(LangageManager.getInstance().getText("connexion_textInfo_unknown_error"));
            }
        }
    }


    public void OnRetourButton(ActionEvent actionEvent) {
        sceneHandler.afficherAccueil();
    }
}
