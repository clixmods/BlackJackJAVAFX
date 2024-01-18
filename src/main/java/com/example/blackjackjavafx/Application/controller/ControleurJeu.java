package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
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

    private String messageRoundTextState = "jeu_messageRoundText_selectMise";

    @FXML
    private HBox cardBoxPlayer;

    @FXML
    private HBox cardBoxDealer;

    @FXML
    private HBox buttonBoxPlayer;

    @FXML
    private Label handPlayerText;

    @FXML
    private Label handDealerText;

    @FXML
    private Button buttonRestartRound;

    @FXML
    private Button buttonStand;

    @FXML
    private Button buttonHit;

    private Button buttonDouble;

    private int gain;

    private SceneHandler sceneHandler;

    private Abstract_SoundJeu sonVictoire;

    private SoundCarte sonCarte;

    private void creerBoutonDoubler(){
        buttonDouble = new Button(LangageManager.getInstance().getText("jeu_doubleButton"));
        buttonDouble.setStyle("-fx-font-size: 12; -fx-text-fill: #9e63f9; -fx-background-color: #24074a; -fx-effect: dropshadow(gaussian, #6718fb, 15, 0, 0, 0.8);");
        buttonDouble.setScaleY(2.0);
        buttonDouble.setScaleX(2.0);
        buttonDouble.setScaleZ(2.0);
        buttonDouble.setOnAction(event -> {
            onDoubleButtonClick();
        });
    }

    public void creerJeu(Client client, int mise, SceneHandler sceneHandler){
        retirerMise();
        this.sceneHandler = sceneHandler;
        creerBoutonDoubler();
        jeu = new Jeu(client, this, mise);
        changerLangue();
        this.client = client;
        setBoutonDouble();
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_cardDistribution"));
        messageRoundTextState = "jeu_messageRoundText_cardDistribution";
        buttonBoxPlayer.setVisible(true);
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_playerTurn"));
        messageRoundTextState = "jeu_messageRoundText_playerTurn";
        jeu.distribuerCartes();
        afficherCarteFaceCacheeCroupier();
        handPlayerText.setVisible(true);
        sonVictoire = new SoundVictoire();
        sonCarte = new SoundCarte();
    }

    @Override
    public void changerLangue() {
        controleurMise.changerLangue();
        if (jeu != null) {
            if (messageRoundTextState.equals("jeu_messageRoundText_victory")){
                messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_victory") + gain + " $");
            }
            else {
                messageRoundText.setText(LangageManager.getInstance().getText(messageRoundTextState));
            }
            mettreAJourAffichageValeurMainJoueur(jeu.getValeurMainJoueur());
            mettreAJourAffichageValeurMainCroupier(jeu.getValeurMainCroupier());
        }
        else {
            messageRoundText.setText(LangageManager.getInstance().getText(messageRoundTextState));
        }
        buttonStand.setText(LangageManager.getInstance().getText("jeu_standButton"));
        buttonHit.setText(LangageManager.getInstance().getText("jeu_hitButton"));
        if (buttonDouble != null) {
            buttonDouble.setText(LangageManager.getInstance().getText("jeu_doubleButton"));
        }
        buttonRestartRound.setText(LangageManager.getInstance().getText("jeu_restartButton"));
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
        if (jeu.peutDoubler()){
            buttonBoxPlayer.getChildren().add(buttonDouble);
        }
    }

    public void finTourJoueur(){
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_dealerTurn"));
        messageRoundTextState = "jeu_messageRoundText_dealerTurn";
        buttonBoxPlayer.setVisible(false);
        cardBoxDealer.getChildren().remove(cardBoxDealer.getChildren().size()-1);
        jeu.tourCroupier();
        handDealerText.setVisible(true);
    }

    public void afficherVictoire(int gain){
        //Affiche l'écran de victoire avec le gain passé en paramètres.
        this.gain = gain;
        ClientService.getInstance().mettreAJourArgentClient(client);
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_victory")+ gain + " $ !!!");
        messageRoundTextState = "jeu_messageRoundText_victory";
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
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_draw"));
        messageRoundTextState = "jeu_messageRoundText_draw";
        ClientService.getInstance().mettreAJourArgentClient(client);
        sceneHandler.mettreAJourHeader();
        buttonRestartRound.setVisible(true);
        sceneHandler.activerBoutonHome(true);
    }

    public void reinitialiserVue(){
        buttonRestartRound.setVisible(false);
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_selectMise"));
        messageRoundTextState = "jeu_messageRoundText_selectMise";
        buttonBoxPlayer.getChildren().remove(buttonDouble);
        cardBoxDealer.getChildren().clear();
        cardBoxPlayer.getChildren().clear();
        vueGlobale.getChildren().remove(vueMise);
        handPlayerText.setVisible(false);
        handDealerText.setVisible(false);
    }

    public void mettreAJourAffichageCartesJoueur(Carte carte){
        cardBoxPlayer.getChildren().add(carte.getImageView());
    }

    public void mettreAJourAffichageCartesCroupier(Carte carte){
        cardBoxDealer.getChildren().add(carte.getImageView());
    }

    public void mettreAJourAffichageValeurMainJoueur(int valeur){
        //Met à jour l'affichage de la valeur de la main du joueur
        handPlayerText.setText(LangageManager.getInstance().getText("jeu_handPlayerText") + valeur);
    }

    public void mettreAJourAffichageValeurMainCroupier(int valeur){
        handDealerText.setText(LangageManager.getInstance().getText("jeu_handDealerText") + valeur);
    }

    public void afficherBlackJack(int gain){
        buttonBoxPlayer.setVisible(false);
        messageRoundText.setText("Blackjack !");
        sonVictoire = new SoundBlackJack();
        // J'aimerais que l'affichage affiche blackJack puis attende un peu avant d'afficher la victoire
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
        afficherVictoire(gain);
    }

    public void afficherDefaite(){
        messageRoundText.setText(LangageManager.getInstance().getText("jeu_messageRoundText_lose"));
        messageRoundTextState = "jeu_messageRoundText_lose";
        buttonRestartRound.setVisible(true);
        buttonBoxPlayer.setVisible(false);
        sceneHandler.activerBoutonHome(true);
    }

    public void onRestartButtonClick(){
        sceneHandler.selectionnerMise(client);
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
