package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControleurRegles implements Controleur{

    @FXML
    private Label label;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    private SceneHandler sceneHandler;
    public void initialiserRegles(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
        LabelResponsive();
    }

    @Override
    public void changerLangue() {
        label.setText(LangageManager.getInstance().getText("regles_label1"));
        label2.setText(LangageManager.getInstance().getText("regles_label2"));
        label3.setText(LangageManager.getInstance().getText("regles_label3"));
        label4.setText(LangageManager.getInstance().getText("regles_label4"));
    }

    private void LabelResponsive(){
        label.setWrapText(true);
        label.setMaxWidth(Double.MAX_VALUE);
        label2.setWrapText(true);
        label2.setMaxWidth(Double.MAX_VALUE);
        label3.setWrapText(true);
        label3.setMaxWidth(Double.MAX_VALUE);
        label4.setWrapText(true);
        label4.setMaxWidth(Double.MAX_VALUE);
    }
}