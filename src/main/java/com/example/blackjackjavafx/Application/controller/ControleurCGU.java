package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Vue.SceneHandler;

public class ControleurCGU implements Controleur{
    private SceneHandler sceneHandler;
    public void initialiserCGU(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
    }
    @Override
    public void changerLangue() {
        // Les CGU ne sont disponibles qu'en français
    }

    public void retour(){
        sceneHandler.afficherInscription();
    }
}
