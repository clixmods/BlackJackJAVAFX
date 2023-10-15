package com.example.blackjackjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneHandler {

    public static Scene sceneInit;
    public static Scene sceneGame;
    private FXMLLoader _gameFxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("game-playerTurn-view.fxml"));
    private FXMLLoader _fxmlLoader = new FXMLLoader(BlackJackApplication.class.getResource("hello-view.fxml"));
    public static Stage _stageApplication;
    public SceneHandler(Stage stage) throws IOException {


        _stageApplication = stage;
        sceneInit = new Scene(_fxmlLoader.load(), 320, 480);
        sceneGame = new Scene(_gameFxmlLoader.load(), 320, 480);




        _stageApplication.setScene(sceneInit);
    }

    public static void SetScene(Scene sceneToLoad)
    {

        _stageApplication.setScene(sceneToLoad);

    }
}
