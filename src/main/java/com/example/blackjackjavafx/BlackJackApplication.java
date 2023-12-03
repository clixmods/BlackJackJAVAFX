package com.example.blackjackjavafx;

import com.example.blackjackjavafx.Application.Service.ClientService;
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
    public static IntegerProperty Money = new SimpleIntegerProperty(1000);

    @Override
    public void start(Stage stage) throws IOException {
        Money = new SimpleIntegerProperty(1000);
        sceneHandler = new SceneHandler(stage);
        stage.setTitle("Black Jack");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}