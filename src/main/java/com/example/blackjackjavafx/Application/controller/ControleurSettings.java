package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageEN;
import com.example.blackjackjavafx.Application.Langage.LangageFR;
import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.helper.SoundsHelper;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Label settingsLabel;

    @FXML
    private Label musicVolumeLabel;

    @FXML
    private Label effectsVolumeLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Button submitButton;

    private double volumeMusique;
    private double volumeEffets;
    private String langue;

    public void initialiserSettings(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
    }

    @Override
    public void changerLangue() {
        settingsLabel.setText(LangageManager.getInstance().getText("settings_settingsLabel"));
        musicVolumeLabel.setText(LangageManager.getInstance().getText("settings_musicVolumeLabel"));
        effectsVolumeLabel.setText(LangageManager.getInstance().getText("settings_effectsVolumeLabel"));
        languageLabel.setText(LangageManager.getInstance().getText("settings_languageLabel"));
        submitButton.setText(LangageManager.getInstance().getText("settings_submitButton"));
    }

    @FXML
    public void handleAppliquer() {
        volumeMusique = sliderMusique.getValue();
        volumeEffets = sliderEffets.getValue();
        langue = langueBox.getValue();


        sceneHandler.reglerVolumeMusique(volumeMusique);
        SoundsHelper.setVolume(volumeEffets);

        //Cette partie permet de changer la langue si elle a été modifiée. Elle fonctionne mais n'est pas très propre (ça ne respecte pas vraiment le principe Open/Close), il faudra la repenser
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