package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.SoundsHelper;
import com.example.blackjackjavafx.Application.sound.Abstract_SoundJeu;
import com.example.blackjackjavafx.Application.sound.SoundBlackJack;
import com.example.blackjackjavafx.Application.sound.SoundCarte;
import com.example.blackjackjavafx.Application.sound.SoundVictoire;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

public class ControleurJeu implements Controleur{


    private Jeu jeu;

    private Client client;

    private Node vueMise;

    private ControleurMise controleurMise;

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

    private Abstract_SoundJeu sonVictoire;

    private SoundCarte sonCarte;

    public void creerJeu(Client client, int mise, SceneHandler sceneHandler){
        retirerMise();
        this.sceneHandler = sceneHandler;
        changerLangue();
        jeu = new Jeu(client, this, mise);
        this.client = client;
        setBoutonDouble();
        messageRoundText.setText("Distribution des cartes");
        buttonBoxPlayer.setVisible(true);
        messageRoundText.setText("Tour joueur");
        jeu.distribuerCartes();
        afficherCarteFaceCacheeCroupier();
        handPlayerText.setVisible(true);
        sonVictoire = new SoundVictoire();
        sonCarte = new SoundCarte();
    }

    @Override
    public void changerLangue() {
        controleurMise.changerLangue();
    }

    public void afficherMise(Client client1, SceneHandler sceneHandler1){
        reinitialiserVue();
        FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
        try {
            vueMise = miseLoader.load();
            vueGlobale.getChildren().add(vueMise);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        controleurMise = miseLoader.getController();
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
        cardBoxDealer.getChildren().remove(cardBoxDealer.getChildren().size()-1);
        jeu.tourCroupier();
    }

    public void afficherVictoire(int mise){
        ClientService.getInstance().mettreAJourArgentClient(client);
        messageRoundText.setText("Vous avez gagné ! Vous remportez "+ mise + " € !!!");
        sceneHandler.mettreAJourHeader();
        buttonRestartRound.setVisible(true);
        sceneHandler.activerBoutonHome(true);


        jouerSonVictoire();
    }

    public void afficherCarteFaceCacheeCroupier(){
        ImageView imageCarteDos = new ImageView();
        InputStream inputStream = getClass().getResourceAsStream("/images/back_card.png");
        if (inputStream != null){
            imageCarteDos.setImage(new Image(inputStream));
            imageCarteDos.setFitWidth(100);
            imageCarteDos.setPreserveRatio(true);
            imageCarteDos.setSmooth(true);
        }
        cardBoxDealer.getChildren().add(imageCarteDos);
    }

    public void afficherEgalite(){
        messageRoundText.setText("Égalité !");
        ClientService.getInstance().mettreAJourArgentClient(client);
        sceneHandler.mettreAJourHeader();
        buttonRestartRound.setVisible(true);
        sceneHandler.activerBoutonHome(true);
    }

    public void reinitialiserVue(){
        messageRoundText.setText("Sélectionnez votre mise");
        cardBoxDealer.getChildren().clear();
        cardBoxPlayer.getChildren().clear();
        vueGlobale.getChildren().remove(vueMise);
        handPlayerText.setVisible(false);
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
        sonVictoire = new SoundBlackJack();
        afficherVictoire(mise);
    }

    public void afficherDefaite(){
        messageRoundText.setText("Vous avez perdu");
        buttonRestartRound.setVisible(true);
        buttonBoxPlayer.setVisible(false);
        sceneHandler.activerBoutonHome(true);
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
        jouerSonCarte();
    }

    public void onDoubleButtonClick(){
        jeu.joueurDouble();
        ClientService.getInstance().mettreAJourArgentClient(client);
        jouerSonCarte();
    }

    public void jouerSonCarte(){
        sonCarte.play(SoundsHelper.getVolume());
    }

    public void jouerSonVictoire(){
        sonVictoire.play(SoundsHelper.getVolume());
    }
}
