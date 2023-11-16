package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.Metier.Carte;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ControleurJeu {

    private Jeu jeu;

    @FXML
    private Label messageRoundText;

    @FXML
    private HBox cardBoxPlayer;

    @FXML
    public HBox cardBoxDealer;

    @FXML
    private Button buttonRestartRound;

    @FXML
    private Button buttonDouble;

    public void creerJeu(Client client, int mise){
        buttonDouble.setVisible(false);
        jeu = new Jeu(client, this, mise);
        messageRoundText.setText("Distribution des cartes");
        jeu.distribuerCartes();
    }

    public void mettreAJourAffichageCartesJoueur(Carte carte){
        cardBoxPlayer.getChildren().add(carte.getImageView());
    }

    public void mettreAJourAffichageCartesCroupier(Carte carte){
        cardBoxDealer.getChildren().add(carte.getImageView());
    }

    public void onRestartButtonClick(){
        System.out.println("bouton restart");
    }

    public void onStandButtonClick(){
        System.out.println("bouton stand");
    }

    public void onHitButtonClick(){
        System.out.println("bouton hit");
    }

    public void onDoubleButtonClick(){
        System.out.println("bouton double");
    }
}
