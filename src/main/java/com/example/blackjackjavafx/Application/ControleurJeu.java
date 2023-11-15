package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.Metier.Carte;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControleurJeu {

    private Jeu jeu;

    @FXML
    private Label messageRoundText;

    public void creerJeu(Client client, int mise){
        jeu = new Jeu(client, this, mise);
        messageRoundText.setText("Distribution des cartes");
        jeu.distribuerCartes();
    }

    public void mettreAJourAffichageCartesJoueur(Carte carte){

    }
}
