package com.example.blackjackjavafx;

import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class BlackJackApplication extends Application {

    public static SceneHandler sceneHandler;

    @Override
    public void start(Stage stage) throws IOException {
        sceneHandler = new SceneHandler(stage);
        stage.setTitle("Black Jack");
        stage.resizableProperty().set(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
