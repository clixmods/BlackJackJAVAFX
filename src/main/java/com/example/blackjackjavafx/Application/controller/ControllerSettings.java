package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

public class ControllerSettings {

    private SceneHandler sceneHandler;
    @FXML
    private Slider sliderMusique;
    @FXML
    private Slider sliderEffets;
    @FXML
    private ComboBox<String> langueBox;
    @FXML
    private ComboBox<String> difficulteBox;

    private double volumeMusique;
    private double volumeEffets;
    private String langue;
    private String difficulte;

    public void initialiserSettings(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }
    @FXML
    public void handleAppliquer() {
        volumeMusique = sliderMusique.getValue();
        volumeEffets = sliderEffets.getValue();
        langue = langueBox.getValue();
        difficulte = difficulteBox.getValue();


        System.out.println("Volume de la musique : " + volumeMusique);
        System.out.println("Volume des effets sonores : " + volumeEffets);
        System.out.println("Langue sélectionnée : " + langue);
        System.out.println("Difficulté sélectionnée : " + difficulte);

    }
}