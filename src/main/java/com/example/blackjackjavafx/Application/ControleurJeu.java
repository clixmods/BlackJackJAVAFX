package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.BlackJackApplication;
import com.example.blackjackjavafx.Metier.Carte;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeu;
import com.example.blackjackjavafx.Vue.SceneHandler;
import com.example.blackjackjavafx.gameState.GameState;
import javafx.event.Event;
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
    private HBox cardBoxDealer;

    @FXML
    private HBox buttonBoxPlayer;

    @FXML
    private Label handPlayerText;

    @FXML
    private Button buttonRestartRound;

    private SceneHandler sceneHandler;

    public void creerJeu(Client client, int mise, SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        buttonRestartRound.setVisible(true);
        jeu = new Jeu(client, this, mise);
        cardBoxDealer.getChildren().clear();
        cardBoxPlayer.getChildren().clear();
        setBoutonDouble();
        messageRoundText.setText("Distribution des cartes");
        jeu.distribuerCartes();
        buttonBoxPlayer.setVisible(true);
    }

    private void setBoutonDouble(){
        //Cette fonction fait apparaître le bouton buttonDouble si le joueur en a les moyens
        if (buttonBoxPlayer.getChildren().size() == 3){
            buttonBoxPlayer.getChildren().remove(2);
        }
        if (jeu.peutDoubler()){
            Button buttonDouble = new Button("Double");
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
        messageRoundText.setText("Fin du tour du joueur");
        buttonBoxPlayer.setVisible(false);
        tourCroupier();
    }

    public void tourCroupier(){

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

    public void afficherBlackJack(){
        messageRoundText.setText("Blackjack !");
        buttonRestartRound.setVisible(true);

    }

    public void afficherDefaite(){
        messageRoundText.setText("Vous avez perdu");
        buttonRestartRound.setVisible(true);
    }

    public void onRestartButtonClick(){
        BlackJackApplication.gameStateInitiater.notify(GameState.SelectionMise);
    }

    public void onStandButtonClick(){
        System.out.println("bouton stand");
    }

    public void onHitButtonClick(){
        System.out.println("bouton hit");
    }

    public void onDoubleButtonClick(){
        jeu.joueurDouble();
    }
}
