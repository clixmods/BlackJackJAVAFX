package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;

public class ControleurAccueil implements Controleur{

    private SceneHandler sceneHandler;

    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
    }

    public void onStartGameButtonClick(){
        if (Connexion.getInstance().estConnecte()) {
            sceneHandler.selectionnerMise(Connexion.getInstance().getClientConnecte());
        }
    }

    public void changerLangue(){

    }

    public void onInscriptionButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherInscription();
    }

    public void onRulesButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherRegles();
    }

    public void onCGUButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherCGU();
    }

    public void onConnexionButtonClick(ActionEvent actionEvent) {
        sceneHandler.afficherConnexion();
    }
}
