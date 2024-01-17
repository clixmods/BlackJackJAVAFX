package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.RepositoryClient;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControleurUser implements Controleur{


    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelArgent;
    @FXML
    private ComboBox<Integer> montantComboBox;


    private SceneHandler sceneHandler;
    public void initialiserUser(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
        updateLabels();
    }
    @Override
    public void changerLangue() {

    }

    @FXML
    public void handleAjouterArgent(){
        Client client = Connexion.getInstance().getClientConnecte();
        if(montantComboBox.getValue() != null){
            client.ajouterArgent(montantComboBox.getValue());
            new RepositoryClient().mettreAJourArgent(client);
        }
        sceneHandler.mettreAJourHeader();
        sceneHandler.afficherUser();
    }

    @FXML
    public void handleDeconnexion(){
        Connexion.getInstance().deconnecter();
        sceneHandler.mettreAJourHeader();
        sceneHandler.afficherAccueil();
    }

    private void updateLabels() {
        Client client = Connexion.getInstance().getClientConnecte();
        labelNom.setText("Nom: " + client.getNom());
        labelPrenom.setText("Prénom: " + client.getPrenom());
        labelEmail.setText("Mail: " + client.getMail());
        labelArgent.setText("Argent sur le compte: $" + client.getArgent());
    }
}
