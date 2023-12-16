package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.Application.controller.*;
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

    private final static FXMLLoader jeuLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private final static FXMLLoader accueilLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    private final static FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
    private final static FXMLLoader inscriptionLoader = new FXMLLoader(BlackJackApplication.class.getResource("inscription-view.fxml"));

    private final static FXMLLoader connexionLoader = new FXMLLoader(BlackJackApplication.class.getResource("connexion-view.fxml"));
    private final static FXMLLoader settingsLoader = new FXMLLoader(BlackJackApplication.class.getResource("settings-view.fxml"));
    private final static FXMLLoader headerLoader = new FXMLLoader(BlackJackApplication.class.getResource("header.fxml"));


    private Stage stageApplication;

    private static StackPane stackPane;

    public SceneHandler(Stage stage) throws IOException {
        stageApplication = stage;
        sceneAccueil = new Scene(accueilLoader.load(),640, 700);
        subSceneMise = new SubScene(miseLoader.load(), 640, 700);
        sceneJeu = new Scene(jeuLoader.load(), 640, 700);
        sceneInscription = new Scene(inscriptionLoader.load(),640,700);
        sceneConnexion = new Scene(connexionLoader.load(),640,700);
        sceneSettings = new Scene(settingsLoader.load(),640,700);

        // Ici on load le header pour tous les affichages
        headerLoader.load();
        ControleurHeader controleurHeader = headerLoader.getController();
        controleurHeader.initialiserHeader(this);

        stackPane = new StackPane();
        stackPane.getChildren().add(sceneJeu.getRoot());

        Image img = new Image("/opera_y1iB56RCrT.png");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundJeu = new Background(bImg);
        stackPane.setBackground(backgroundJeu);
        sceneJeu.setRoot(stackPane);

        stageApplication.setScene(sceneAccueil);
        afficherAccueil();
    }

    public void afficherAccueil(){
        ControleurAccueil controleurAccueil = accueilLoader.getController();
        controleurAccueil.initialiserAccueil(this);
        stageApplication.setScene(sceneAccueil);
    }

    public void afficherSettings(){
        ControllerSettings controllerSettings = settingsLoader.getController();
        controllerSettings.initialiserSettings(this);
        stageApplication.setScene(sceneSettings);
    }

    public void afficherInscription(){
        stageApplication.setScene(sceneInscription);
        ControleurInscription controleurInscription = inscriptionLoader.getController();
        controleurInscription.initialiserInscription(this);
    }

    public void afficherConnexion(){
        stageApplication.setScene(sceneConnexion);
        ControleurConnexion controleurConnexion = connexionLoader.getController();
        controleurConnexion.initialiserConnexion(this);
    }


    public void selectionnerMise(Client client){
        stageApplication.setScene(sceneJeu);
        ControleurMise controleurMise = miseLoader.getController();
        controleurMise.creerMise(client, this);
        stackPane.getChildren().add(subSceneMise);
        subSceneMise.setVisible(true);
    }

    public void commencerPartie(Client client, int mise){
        ControleurJeu controleurJeu = jeuLoader.getController();
        controleurJeu.creerJeu(client, mise, this);
        stackPane.getChildren().remove(subSceneMise);
        subSceneMise.setVisible(false);
    }
}
