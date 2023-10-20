package com.example.blackjackjavafx;

import com.example.blackjackjavafx.gameState.GameState;
import com.example.blackjackjavafx.notifier.Listener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneHandler implements Initializable {

    public static Scene sceneInit;
    public static Scene sceneGame;
    public static SubScene  subSceneMise;
    private static FXMLLoader _gameFxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private static FXMLLoader _fxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    private static FXMLLoader _miseFxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-miseSelection-view.fxml"));
    public static Stage _stageApplication;
    private final StackPane stackPane;

    public SceneHandler(Stage stage) throws IOException {
        BlackJackApplication.gameStateInitiater.addListener(new Listener<GameState>() {
            @Override
            public void onEvent(GameState event) {
                switch (event) {
                    case MainMenu -> {

                    }
                    case SelectionMise -> {
                        stackPane.getChildren().add(subSceneMise); // Ajoutez la SubScene "mise" au StackPane
                        subSceneMise.setVisible(true);
                    }
                    case StartRound -> {
                        stackPane.getChildren().remove(subSceneMise); // Retirer la SubScene "mise" au StackPane
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
        });
        Group root = new Group();
        _stageApplication = stage;
        sceneInit = new Scene(_fxmlLoader.load(), 640, 700);
        sceneGame = new Scene(_gameFxmlLoader.load(), 640, 700);
        // Chargez la scène "mise" dans une SubScene
        subSceneMise = new SubScene(_miseFxmlLoader.load(), 640, 700);

        // Créez un StackPane pour contenir la scène "game" et la SubScene "mise"
        stackPane = new StackPane();
        stackPane.getChildren().add(sceneGame.getRoot()); // Ajoutez la scène "game" au StackPane

        // Configurez la scène "game" pour utiliser le StackPane comme racine
        sceneGame.setRoot(stackPane);

        _stageApplication.setScene(sceneInit);
    }

    public static void SetScene(Scene sceneToLoad)
    {
        _stageApplication.setScene(sceneToLoad);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
