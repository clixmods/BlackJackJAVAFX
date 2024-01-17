package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageEN;
import com.example.blackjackjavafx.Application.Langage.LangageFR;
import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

public class ControleurSettings implements Controleur{

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
        changerLangue();
    }

    @Override
    public void changerLangue() {

    }

    @FXML
    public void handleAppliquer() {
        volumeMusique = sliderMusique.getValue();
        volumeEffets = sliderEffets.getValue();
        langue = langueBox.getValue();
        difficulte = difficulteBox.getValue();


        sceneHandler.getMusicPlayer().reglerVolumeMusique(volumeMusique);
        System.out.println("Volume des effets sonores : " + volumeEffets);
        System.out.println("Langue sélectionnée : " + langue);
        System.out.println("Difficulté sélectionnée : " + difficulte);

        //Cette partie permet de changer la langue si elle a été modifiée. Elle fonctionne mais n'est pas très propre (ça ne respecte pas vraiment le principe Open/Close), il faudra la recoder
        if (langue.equals("Français")){
            if (!LangageManager.getInstance().getText("connexion_passwordFieldDescriptor").equals("Mot de passe")){
                LangageManager.setInstance(LangageFR.getInstance());
                sceneHandler.changerLangue();
            }
        }
        else {
            if (!LangageManager.getInstance().getText("connexion_passwordFieldDescriptor").equals("Password")){
                LangageManager.setInstance(LangageEN.getInstance());
                sceneHandler.changerLangue();
            }
        }

        sceneHandler.enleverSettings();
    }
}