package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.Application.controller.*;
import com.example.blackjackjavafx.Application.music.MusicPlayer;
import com.example.blackjackjavafx.BlackJackApplication;
import com.example.blackjackjavafx.Metier.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHandler {

    private static Scene sceneAccueil;
    private static Scene sceneJeu;
    private static SubScene subSceneMise;
    private static Scene sceneInscription;
    private static Scene sceneConnexion;
    private static Scene sceneSettings;
    private static Scene sceneRegles;
    private static Scene sceneCGU;
    private static Scene sceneUser;

    private final static FXMLLoader jeuLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private final static FXMLLoader accueilLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    private final static FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
    private final static FXMLLoader inscriptionLoader = new FXMLLoader(BlackJackApplication.class.getResource("inscription-view.fxml"));
    private final static FXMLLoader settingsLoader = new FXMLLoader(BlackJackApplication.class.getResource("settings-view.fxml"));
    private final static FXMLLoader headerLoader = new FXMLLoader(BlackJackApplication.class.getResource("header.fxml"));
    private final static FXMLLoader connexionLoader = new FXMLLoader(BlackJackApplication.class.getResource("connexion-view.fxml"));
    private final static FXMLLoader reglesLoader = new FXMLLoader(BlackJackApplication.class.getResource("regles-view.fxml"));
    private final static FXMLLoader cguLoader = new FXMLLoader(BlackJackApplication.class.getResource("cgu-view.fxml"));
    private final static FXMLLoader userLoader = new FXMLLoader(BlackJackApplication.class.getResource("user-view.fxml"));

    private Stage stageApplication;
    private VueGenerale vueGenerale;
    private static StackPane stackPane;
    private static StackPane stackpaneAccueil;
    private static StackPane stackpaneCGU;
    private static StackPane stackPaneConnexion;
    private static StackPane stackPaneInscription;
    private static StackPane stackPaneRegles;
    private static StackPane stackPaneUser;
    private MusicPlayer musicPlayer;

    public SceneHandler(Stage stage) throws IOException {
        stageApplication = stage;
        sceneAccueil = new Scene(accueilLoader.load(),640, 700 );
        subSceneMise = new SubScene(miseLoader.load(), 640, 700);
        sceneJeu = new Scene(jeuLoader.load(), 640, 700);
        sceneInscription = new Scene(inscriptionLoader.load(),640,700);
        sceneConnexion = new Scene(connexionLoader.load(),640,700);
        sceneRegles = new Scene(reglesLoader.load());
        sceneCGU = new Scene(cguLoader.load(), 640, 700);
        sceneUser = new Scene(userLoader.load(), 640, 700);

        vueGenerale = new VueGenerale(headerLoader, settingsLoader, this);

        stackPane = new StackPane();
        stackPane.getChildren().add(sceneJeu.getRoot());
        Image img = new Image("/backgroundJeu.png");
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, true, true);
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background backgroundJeu = new Background(bImg);
        stackPane.setBackground(backgroundJeu);
        sceneJeu.setRoot(stackPane);

        stackpaneAccueil =new StackPane();
        stackpaneAccueil.getChildren().add(sceneAccueil.getRoot());
        Image bgAccueil =new Image("/backgroundAccueil.png");
        BackgroundImage bgimgAccueil = new BackgroundImage(bgAccueil,BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, true, true));
        Background backgroundAccueil = new Background(bgimgAccueil);
        stackpaneAccueil.setBackground(backgroundAccueil);
        sceneAccueil.setRoot(stackpaneAccueil);

        stackpaneCGU =new StackPane();
        stackpaneCGU.getChildren().add(sceneCGU.getRoot());
        Image bgCGU =new Image("/backgroundMenu.jpg");
        BackgroundImage bgimgCGU = new BackgroundImage(bgCGU,BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background backgroundMenu = new Background(bgimgCGU);
        stackpaneCGU.setBackground(backgroundMenu);
        sceneCGU.setRoot(stackpaneCGU);

        stackPaneConnexion =new StackPane();
        stackPaneConnexion.getChildren().add(sceneConnexion.getRoot());
        stackPaneConnexion.setBackground(backgroundMenu);
        sceneConnexion.setRoot(stackPaneConnexion);

        stackPaneInscription = new StackPane();
        stackPaneInscription.getChildren().add(sceneInscription.getRoot());
        stackPaneInscription.setBackground(backgroundMenu);
        sceneInscription.setRoot(stackPaneInscription);

        stackPaneRegles = new StackPane();
        stackPaneRegles.getChildren().add(sceneRegles.getRoot());
        stackPaneRegles.setBackground(backgroundMenu);
        sceneRegles.setRoot(stackPaneRegles);

        stackPaneUser = new StackPane();
        stackPaneUser.getChildren().add(sceneUser.getRoot());
        stackPaneUser.setBackground(backgroundMenu);
        sceneUser.setRoot(stackPaneUser);

        Scene sceneGenerale = new Scene(vueGenerale, 1400,900);
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

    public void afficherCGU(){
        ControleurCGU controleurCGU = cguLoader.getController();
        controleurCGU.initialiserCGU(this);
        vueGenerale.setCentre(sceneCGU.getRoot(), controleurCGU);
    }

    public void afficherUser(){
        ControleurUser controleurUser = userLoader.getController();
        controleurUser.initialiserUser(this);
        vueGenerale.setCentre(sceneUser.getRoot(), controleurUser);
    }


    public void selectionnerMise(Client client){
        ControleurJeu controleurJeu = jeuLoader.getController();
        controleurJeu.afficherMise(client, this);
        vueGenerale.setCentre(sceneJeu.getRoot(), controleurJeu);
    }

    public void mettreAJourHeader(){
        vueGenerale.miseAJourHeader();
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
