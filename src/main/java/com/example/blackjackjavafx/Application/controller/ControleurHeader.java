package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class ControleurHeader implements Controleur{

    private boolean boutonHomeActif;
    private static SceneHandler sceneHandler;
    @FXML
    private ImageView boutonHome;
    @FXML
    private Label labelNomCompte;
    @FXML
    private Label labelArgent;

    @FXML
    private ImageView boutonSettings;


    public void initialiserHeader(SceneHandler sceneHandler){

        ControleurHeader.sceneHandler = sceneHandler;

        changerLangue();

        InputStream inputStream = getClass().getResourceAsStream("/images.header/home.png");
        if(inputStream != null) {
            Image homeImage = new Image(inputStream);
            boutonHome.setImage(homeImage);
            boutonHome.setFitWidth(25);
            boutonHome.setFitHeight(25);
        }

        InputStream inputStream2 = getClass().getResourceAsStream("/images.header/settings.png");
        if(inputStream != null) {
            Image settingsImage = new Image(inputStream2);
            boutonSettings.setImage(settingsImage);
            boutonSettings.setFitWidth(25);
            boutonSettings.setFitHeight(25);
        }

        boutonHomeActif = true;

    }

    public void changerLangue(){
        if (!Connexion.getInstance().estConnecte()) {
            labelNomCompte.setText(LangageManager.getInstance().getText("header_nomCompteLabel_disconnected"));
        }
    }

    @FXML
    public void handleHome() {
        if(boutonHomeActif) sceneHandler.afficherAccueil();
    }

    @FXML
    public void handleSettings() {
        sceneHandler.afficherSettings();
    }

    public void setNomCompte(String nomCompte) {
        labelNomCompte.setText(nomCompte);
    }

    public void setArgent(double argent) {
        labelArgent.setText(argent + "$");
    }

    public void activerBoutonHome(boolean active){
        boutonHomeActif = active;
        if(active) boutonHome.setOpacity(1.0);
        else boutonHome.setOpacity(0.5);
    }
}