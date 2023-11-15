package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.BlackJackApplication;
import com.example.blackjackjavafx.gameState.GameState;
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

    private final static FXMLLoader jeuLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private final static FXMLLoader accueilLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    private final static FXMLLoader miseLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));

    private Stage stageApplication;

    private StackPane stackPane;

    public SceneHandler(Stage stage) throws IOException {
        stageApplication = stage;
        sceneAccueil = new Scene(accueilLoader.load(),640, 700 );
        subSceneMise = new SubScene(miseLoader.load(), 640, 700);
        sceneJeu = new Scene(jeuLoader.load(), 640, 700);

        stackPane = new StackPane();
        stackPane.getChildren().add(sceneJeu.getRoot());

        Image img = new Image("/opera_y1iB56RCrT.png");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background backgroundJeu = new Background(bImg);
        stackPane.setBackground(backgroundJeu);
        sceneJeu.setRoot(stackPane);

        stageApplication.setScene(sceneAccueil);
    }

    public void changerScene(GameState evenement){
        switch (evenement) {
            case MainMenu -> {

            }
            case SelectionMise -> {
                stackPane.getChildren().add(subSceneMise);
                subSceneMise.setVisible(true);
            }
            case StartRound -> {
                stackPane.getChildren().remove(subSceneMise);
                subSceneMise.setVisible(false);
            }
            case DealerTurn -> {
            }
            case ComparateCards -> {
            }
            case EndOfRound -> {
            }
        }
    }
}
