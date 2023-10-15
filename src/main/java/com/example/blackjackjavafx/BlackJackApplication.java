package com.example.blackjackjavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class BlackJackApplication extends Application {
    public static GameState state;
    public static SceneHandler sceneHandler;


    @Override
    public void start(Stage stage) throws IOException {

        sceneHandler = new SceneHandler(stage);
        stage.setTitle("Black Jack");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}