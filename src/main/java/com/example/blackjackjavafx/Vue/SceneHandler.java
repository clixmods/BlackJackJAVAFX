package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Application.controller.*;
import com.example.blackjackjavafx.Application.helper.SoundsHelper;
import com.example.blackjackjavafx.Application.music.MusicPlayer;
import com.example.blackjackjavafx.Application.sound.SoundBlackJack;
import com.example.blackjackjavafx.Application.sound.SoundCarte;
import com.example.blackjackjavafx.Application.sound.SoundJeton;
import com.example.blackjackjavafx.Application.sound.SoundVictoire;
import com.example.blackjackjavafx.BlackJackApplication;
import com.example.blackjackjavafx.Metier.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class SceneHandler {

    private static Scene sceneAccueil;
    private static Scene sceneJeu;
    private static SubScene subSceneMise;
    private static Scene sceneInscription;
    private static Scene sceneConnexion;
    private static Scene sceneSettings;
    private static Scene sceneRegles;


    private final static FXMLLoader jeuLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private final static FXMLLoader accueilLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    private final static FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
    private final static FXMLLoader inscriptionLoader = new FXMLLoader(BlackJackApplication.class.getResource("inscription-view.fxml"));
    private final static FXMLLoader settingsLoader = new FXMLLoader(BlackJackApplication.class.getResource("settings-view.fxml"));
    private final static FXMLLoader headerLoader = new FXMLLoader(BlackJackApplication.class.getResource("header.fxml"));
    private final static FXMLLoader connexionLoader = new FXMLLoader(BlackJackApplication.class.getResource("connexion-view.fxml"));
    private final static FXMLLoader reglesLoader = new FXMLLoader(BlackJackApplication.class.getResource("regles-view.fxml"));

    private Stage stageApplication;
    private VueGenerale vueGenerale;
    private static StackPane stackPane;
    private MusicPlayer musicPlayer;


    public SceneHandler(Stage stage) throws IOException {
        stageApplication = stage;
        sceneAccueil = new Scene(accueilLoader.load(),640, 700 );
        subSceneMise = new SubScene(miseLoader.load(), 640, 700);
        sceneJeu = new Scene(jeuLoader.load(), 640, 700);
        sceneInscription = new Scene(inscriptionLoader.load(),640,700);
        sceneConnexion = new Scene(connexionLoader.load(),640,700);
        sceneRegles = new Scene(reglesLoader.load());


        vueGenerale = new VueGenerale(headerLoader, settingsLoader, this);

        stackpaneJeu = new StackPane();
        stackpaneJeu.getChildren().add(sceneJeu.getRoot());

        Image img = new Image("/backgroundJeu.png");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background backgroundJeu = new Background(bImg);

        stackpaneJeu.setBackground(backgroundJeu);
        sceneJeu.setRoot(stackpaneJeu);

        stackpaneAccueil = new StackPane();
        stackpaneAccueil.getChildren().add(sceneAccueil.getRoot());

        Image imgAccueil = new Image("/fondecranaccueil.png");
        BackgroundImage bImgAccueil = new BackgroundImage(imgAccueil, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundAccueil = new Background(bImgAccueil);

        stackpaneAccueil.setBackground(backgroundAccueil);
        sceneAccueil.setRoot(stackpaneAccueil);

        Scene sceneGenerale = new Scene(vueGenerale, 1000,700);
        stageApplication.setScene(sceneGenerale);

        afficherSettings();
        afficherAccueil();

        musicPlayer = new MusicPlayer();

    }

    public void afficherAccueil() {
        ControleurAccueil controleurAccueil = accueilLoader.getController();
        controleurAccueil.initialiserAccueil(this);

        vueGenerale.setCentre(sceneAccueil.getRoot(), controleurAccueil);
    }

    public void afficherSettings() {
        vueGenerale.setParametres();
    }

    public void enleverSettings(){
        vueGenerale.unsetParametres();
    }

    public void afficherInscription() {
        ControleurInscription controleurInscription = inscriptionLoader.getController();
        controleurInscription.initialiserInscription(this);

        vueGenerale.setCentre(sceneInscription.getRoot(), controleurInscription);
    }

    public void afficherConnexion() {
        ControleurConnexion controleurConnexion = connexionLoader.getController();
        controleurConnexion.initialiserConnexion(this);

        vueGenerale.setCentre(sceneConnexion.getRoot(), controleurConnexion);
    }

    public void afficherRegles() {
        ControleurRegles controleurRegles = reglesLoader.getController();
        controleurRegles.initialiserRegles(this);

        vueGenerale.setCentre(sceneRegles.getRoot(), controleurRegles);

    }


    public void selectionnerMise(Client client){
        ControleurJeu controleurJeu = jeuLoader.getController();
        controleurJeu.afficherMise(client, this);
        vueGenerale.setCentre(sceneJeu.getRoot(), controleurJeu);
    }

    public void mettreAJourHeader(){
        Client client = Connexion.getInstance().getClientConnecte();
        vueGenerale.miseAJourHeader(client);
    }

    public void commencerPartie(Client client, int mise){
        activerBoutonHome(false);
        ControleurJeu controleurJeu = jeuLoader.getController();
        controleurJeu.creerJeu(client, mise, this);
    }

    public void changerLangue(){
        vueGenerale.changerLangue();
    }

    public void activerBoutonHome(boolean active){
        vueGenerale.activerBoutonHome(active);
    }

    public void reglerVolumeMusique(double volume){
        musicPlayer.reglerVolumeMusique(volume);
    }

}
