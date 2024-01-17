package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControleurAccueil implements Controleur{

    @FXML
    private Button startGameButton;

    @FXML
    private Button connectionButton;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button termsAndConditionsButton;

    private SceneHandler sceneHandler;

    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
    }

    public void onStartGameButtonClick(){
        if (Connexion.getInstance().estConnecte()) {
            sceneHandler.selectionnerMise(Connexion.getInstance().getClientConnecte());
        }
    }

    public void changerLangue(){
        startGameButton.setText(LangageManager.getInstance().getText("accueil_startGameButton"));
        connectionButton.setText(LangageManager.getInstance().getText("accueil_connectionButton"));
        inscriptionButton.setText(LangageManager.getInstance().getText("accueil_inscriptionButton"));
        rulesButton.setText(LangageManager.getInstance().getText("accueil_rulesButton"));
        termsAndConditionsButton.setText(LangageManager.getInstance().getText("accueil_termsAndConditionsButton"));
    }

    public void onInscriptionButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherInscription();
    }

    public void onRulesButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherRegles();
    }

    public void onCGUButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherCGU();
    }

    public void onConnexionButtonClick(ActionEvent actionEvent) {
        sceneHandler.afficherConnexion();
    }
}
