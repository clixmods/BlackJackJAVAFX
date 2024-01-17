package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeton;
import com.example.blackjackjavafx.Metier.Miser;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ControleurMise implements Controleur{

    private Client client;
    private int argentJoueur;
    private SceneHandler sceneHandler;
    private Miser miser;

    @FXML
    private Label argentJoueurText;
    @FXML
    private Button buttonValidMise;
    @FXML
    private HBox hBoxJetons;
    @FXML
    private Label miseJoueurText;

    public void creerMise(Client client, SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
        miser = new Miser(client, this);
        argentJoueur = client.getArgent();
        this.client = client;
        mettreAJourAffichage();
    }

    public void changerLangue(){

    }

    private void genererJetons(){
        hBoxJetons.getChildren().clear();
        for (Jeton jeton : miser.obtenirJetons()){
            Button boutonJeton = new Button();
            boutonJeton.maxWidth(100);
            boutonJeton.maxHeight(100);
            boutonJeton.setGraphic(jeton.getImageView());
            boutonJeton.setStyle(
                    "-fx-background-radius: 140em; " +
                    "-fx-min-width: 82px; " +
                    "-fx-min-height: 82px; " +
                    "-fx-max-width: 82; " +
                    "-fx-max-height: 82px;");
            boutonJeton.setPadding(Insets.EMPTY);

            boutonJeton.setOnAction(actionEvent -> {
                argentJoueur -= jeton.getValeur();
                miser.ajoutMise(jeton.getValeur());
            }
            );
            hBoxJetons.getChildren().add(boutonJeton);
        }
    }

    public void mettreAJourAffichage(){
        genererJetons();
        sceneHandler.mettreAJourHeader();
        miseJoueurText.setText("Votre mise : " + miser.getMise());
        argentJoueurText.setText("Votre argent : " + argentJoueur + " €");
    }

    public void onValidButtonClick(){
        if (miser.getMise() > 0) {
            sceneHandler.commencerPartie(client, miser.getMise());
            ClientService.getInstance().mettreAJourArgentClient(client);
        }
        else {
            miseJoueurText.setText("Vous ne pouvez jouer si vous ne misez pas.");
        }
    }

    public void onAjoutezArgentButtonClick(){

    }
}
