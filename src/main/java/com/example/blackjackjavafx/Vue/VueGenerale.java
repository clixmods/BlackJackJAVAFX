package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.Application.controller.ControleurHeader;
import com.example.blackjackjavafx.BlackJackApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class VueGenerale extends BorderPane {

    private ControleurHeader controleurHeader;

    public VueGenerale(FXMLLoader headerLoader, SceneHandler sceneHandler){
        try{
            setTop(headerLoader.load());
            controleurHeader = headerLoader.getController();
            controleurHeader.initialiserHeader(sceneHandler);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ControleurHeader getControleurHeader() {
        return controleurHeader;
    }

    public void setCentre(javafx.scene.Node centre){
        setCenter(centre);
    }
}