package com.example.blackjackjavafx.Application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControleurHeader {

    @FXML
    private Button boutonHome;
    @FXML
    private Label labelNomCompte;
    @FXML
    private Label labelArgentCourant;

    @FXML
    private Button boutonSettings;

    @FXML
    public void handleHome() {
        System.out.println("Bouton Home cliqué !");
    }

    @FXML
    public void handleSettings() {
        System.out.println("Bouton Settings cliqué !");
    }

    public void setNomCompte(String nomCompte) {
        labelNomCompte.setText(nomCompte);
    }

    public void setArgent(double argent) {
        labelArgentCourant.setText(argent + "$");
    }
}