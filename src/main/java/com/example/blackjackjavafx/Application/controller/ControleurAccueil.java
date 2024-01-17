package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControleurAccueil {

    private SceneHandler sceneHandler;

    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }

    public void onStartGameButtonClick(){
        if (Connexion.getInstance().estConnecte()) {
            sceneHandler.selectionnerMise(Connexion.getInstance().getClientConnecte());
        }

    }

    public void onInscriptionButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherInscription();
    }

    public void onConnexionButtonClick(ActionEvent actionEvent) {
        sceneHandler.afficherConnexion();
    }
}
