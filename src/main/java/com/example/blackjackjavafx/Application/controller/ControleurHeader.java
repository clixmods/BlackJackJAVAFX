package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControleurHeader {

    private static SceneHandler sceneHandler;
    @FXML
    private Button boutonHome;
    @FXML
    private Label labelNomCompte;
    @FXML
    private Label labelArgentCourant;

    @FXML
    private Button boutonSettings;

    public void initialiserHeader(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }
    @FXML
    public void handleHome() {
        sceneHandler.afficherAccueil();
    }

    @FXML
    public void handleSettings() {
        sceneHandler.afficherSettings();
    }

    public void setNomCompte(String nomCompte) {
        labelNomCompte.setText(nomCompte);
    }

    public void setArgent(double argent) {
        labelArgentCourant.setText(argent + "$");
    }
}