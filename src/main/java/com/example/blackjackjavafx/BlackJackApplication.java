package com.example.blackjackjavafx;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class BlackJackApplication extends Application {

    public static SceneHandler sceneHandler;

    @Override
    public void start(Stage stage) throws IOException {
        // En mode hors ligne, on joue directement avec le compte invité
        if (ClientService.getInstance().estHorsLigne()) {
            Connexion.getInstance().connecterInvite();
        }
        sceneHandler = new SceneHandler(stage);
        stage.setTitle("Black Jack");
        stage.resizableProperty().set(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
