package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.Application.controller.Controleur;
import com.example.blackjackjavafx.Application.controller.ControleurHeader;
import com.example.blackjackjavafx.Application.controller.ControleurSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class VueGenerale extends BorderPane {

    private StackPane centre;

    private SubScene parametres;

    private ControleurSettings controleurSettings;

    private ControleurHeader controleurHeader;

    private Controleur controleurCourant;

    public VueGenerale(FXMLLoader headerLoader, FXMLLoader settingsLoader, SceneHandler sceneHandler){
        centre = new StackPane();
        setCenter(centre);
        try{
            setTop(headerLoader.load());
            parametres = new SubScene(settingsLoader.load(), 800, 800);
            controleurHeader = headerLoader.getController();
            controleurHeader.initialiserHeader(sceneHandler);
            controleurSettings = settingsLoader.getController();
            controleurSettings.initialiserSettings(sceneHandler);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ControleurHeader getControleurHeader() {
        return controleurHeader;
    }

    public void changerLangue(){
        controleurCourant.changerLangue();
        controleurSettings.changerLangue();
        controleurHeader.changerLangue();
    }

    public void setCentre(javafx.scene.Node centre, Controleur controleur){
        this.centre.getChildren().clear();
        this.centre.getChildren().add(centre);
        controleurCourant = controleur;
    }

    public void setParametres(){
        centre.getChildren().add(parametres);
    }

    public void unsetParametres(){
        centre.getChildren().remove(parametres);
    }

    public void miseAJourHeader(){
        controleurHeader.changerLangue();
    }

    public void activerBoutonHome(boolean active){
        controleurHeader.activerBoutonHome(active);
    }
}