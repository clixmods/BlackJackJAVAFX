package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.BlackJackApplication;
import com.example.blackjackjavafx.Metier.Carte;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeu;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControleurJeu {

    private Jeu jeu;

    private Client client;

    private Node vueMise;

    @FXML
    private VBox vueGlobale;

    @FXML
    private Label messageRoundText;

    @FXML
    private HBox cardBoxPlayer;

    @FXML
    private HBox cardBoxDealer;

    @FXML
    private HBox buttonBoxPlayer;

    @FXML
    private Label handPlayerText;

    @FXML
    private Button buttonRestartRound;

    private SceneHandler sceneHandler;

    public void creerJeu(Client client, int mise, SceneHandler sceneHandler){
        retirerMise();
        this.sceneHandler = sceneHandler;
        jeu = new Jeu(client, this, mise);
        cardBoxDealer.getChildren().clear();
        cardBoxPlayer.getChildren().clear();
        setBoutonDouble();
        messageRoundText.setText("Distribution des cartes");
        buttonBoxPlayer.setVisible(true);
        messageRoundText.setText("Tour joueur");
        jeu.distribuerCartes();
        this.client = client;
    }

    public void afficherMise(Client client1, SceneHandler sceneHandler1){
        FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
        try {
            vueMise = miseLoader.load();
            vueGlobale.getChildren().add(vueMise);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        ControleurMise controleurMise = miseLoader.getController();
        controleurMise.creerMise(client1, sceneHandler1);
    }

    public void retirerMise(){
        vueGlobale.getChildren().remove(vueMise);
    }

    private void setBoutonDouble(){
        //Cette fonction fait apparaître le bouton buttonDouble si le joueur en a les moyens
        if (buttonBoxPlayer.getChildren().size() == 3){
            buttonBoxPlayer.getChildren().remove(2);
        }
        if (jeu.peutDoubler()){
            Button buttonDouble = new Button("Doubler");
            buttonDouble.setScaleY(2.0);
            buttonDouble.setScaleX(2.0);
            buttonDouble.setScaleZ(2.0);
            buttonDouble.setOnAction(event -> {
                onDoubleButtonClick();
            });
            buttonBoxPlayer.getChildren().add(buttonDouble);
        }
    }

    public void finTourJoueur(){
        messageRoundText.setText("Tour croupier");
        buttonBoxPlayer.setVisible(false);
        jeu.tourCroupier();
    }

    public void afficherVictoire(int mise){
        ClientService.getInstance().mettreAJourArgentClient(client);
        messageRoundText.setText("Vous avez gagné ! Vous remportez "+ mise + " € !!!");
        buttonRestartRound.setVisible(true);
    }

    public void afficherEgalite(){
        ClientService.getInstance().mettreAJourArgentClient(client);
        buttonRestartRound.setVisible(true);
        messageRoundText.setText("Égalité !");
    }

    public void mettreAJourAffichageCartesJoueur(Carte carte){
        cardBoxPlayer.getChildren().add(carte.getImageView());
    }

    public void mettreAJourAffichageCartesCroupier(Carte carte){
        cardBoxDealer.getChildren().add(carte.getImageView());
    }

    public void mettreAJourAffichageValeurMainJoueur(int valeur){
        handPlayerText.setText("Main du joueur : " + valeur);
    }

    public void afficherBlackJack(int mise){
        buttonBoxPlayer.setVisible(false);
        messageRoundText.setText("Blackjack !");
        // J'aimerais que l'affichage affiche blackJack puis attende un peu avant d'afficher la victoire
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
        afficherVictoire(mise);
    }

    public void afficherDefaite(){
        messageRoundText.setText("Vous avez perdu");
        buttonRestartRound.setVisible(true);
        buttonBoxPlayer.setVisible(false);
    }

    public void onRestartButtonClick(){
        sceneHandler.selectionnerMise(client);
        buttonRestartRound.setVisible(false);
    }

    public void onStandButtonClick(){
        finTourJoueur();
    }

    public void onHitButtonClick(){
        jeu.joueurPiocheEtGagne();
    }

    public void onDoubleButtonClick(){
        jeu.joueurDouble();
        ClientService.getInstance().mettreAJourArgentClient(client);
    }
}
