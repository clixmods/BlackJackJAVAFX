package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControleurCGU implements Controleur{
        private SceneHandler sceneHandler;
        public void initialiserCGU(SceneHandler sceneHandler){
            this.sceneHandler = sceneHandler;
            changerLangue();
        }
        @Override
        public void changerLangue() {

        }
}
