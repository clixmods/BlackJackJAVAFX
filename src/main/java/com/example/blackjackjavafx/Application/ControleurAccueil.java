package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;

public class ControleurAccueil {

    private SceneHandler sceneHandler;
    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }

    public void onStartGameButtonClick(){
        Client roger = new Client("Roger");
        roger.ajouterArgent(500);
        sceneHandler.selectionnerMise(roger);
    }
}
