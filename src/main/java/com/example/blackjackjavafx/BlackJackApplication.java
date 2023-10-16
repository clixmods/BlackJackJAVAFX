package com.example.blackjackjavafx;

import com.example.blackjackjavafx.gameState.GameStateNotifier;
import com.example.blackjackjavafx.mise.MiseNotifier;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class BlackJackApplication extends Application {

    public static GameStateNotifier gameStateInitiater;
    public static MiseNotifier miseNotifier;
    public static SceneHandler sceneHandler;
    public static List<PlayerHandler> players;
    private static PlayerHandler _playerHandler;
    public static PlayerHandler GetClientPlayer(){ return _playerHandler; }

    @Override
    public void start(Stage stage) throws IOException {
        _playerHandler = new PlayerHandler();
        gameStateInitiater = new GameStateNotifier();
        miseNotifier = new MiseNotifier();
        sceneHandler = new SceneHandler(stage);
        stage.setTitle("Black Jack");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}