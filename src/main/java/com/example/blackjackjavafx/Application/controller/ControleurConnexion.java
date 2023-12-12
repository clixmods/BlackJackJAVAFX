package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Application.connection.ConnexionResult;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleurConnexion {
    public TextField inputLogin;
    public PasswordField inputPassword;
    public Button buttonConnexion;
    public Label textInfo;

    private SceneHandler sceneHandler;
    public void initialiserConnexion(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
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
                textInfo.setText("Connexion réussi");
                // TODO : quest ce qu'on fait ?
            }
            case BAD_PASSWORD -> {
                textInfo.setText("Mot de passe incorrect");
            }
            case UNDEFINED_LOGIN -> {
                textInfo.setText("Login inexistant");
            }
            case UNKNOWN_ERROR -> {
                textInfo.setText("Erreur inconnu");
            }
        }
    }


}
