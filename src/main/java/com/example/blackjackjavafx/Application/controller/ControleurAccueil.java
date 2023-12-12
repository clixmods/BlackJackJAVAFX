package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;

public class ControleurAccueil {

    private SceneHandler sceneHandler;
    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }

    public void onStartGameButtonClick(){
        ClientService clientService = ClientService.getInstance();
        Client loggedClient = clientService.getClients().get(0);
        sceneHandler.selectionnerMise(loggedClient);
    }

    public void onInscriptionButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherInscription();
    }
}
