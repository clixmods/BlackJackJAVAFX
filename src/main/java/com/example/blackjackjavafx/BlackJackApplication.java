package com.example.blackjackjavafx;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.SoundsHelper;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class BlackJackApplication extends Application {


    public static com.example.blackjackjavafx.Vue.SceneHandler sceneHandler;

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