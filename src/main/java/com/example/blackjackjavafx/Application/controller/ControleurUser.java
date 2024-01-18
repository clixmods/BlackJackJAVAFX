package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.connection.Connexion;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.RepositoryClient;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControleurUser implements Controleur{

    @FXML
    private Label userInformationTitle;

    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelArgent;

    @FXML
    private Label addMoneyLabel;

    @FXML
    private ComboBox<Integer> montantComboBox;

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button boutonDeconnexion;


    private SceneHandler sceneHandler;
    public void initialiserUser(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        changerLangue();
    }
    @Override
    public void changerLangue() {
        Client client = Connexion.getInstance().getClientConnecte();
        userInformationTitle.setText(LangageManager.getInstance().getText("user_userInformationTitle"));
        labelNom.setText(LangageManager.getInstance().getText("user_nameLabel") + client.getNom());
        labelPrenom.setText(LangageManager.getInstance().getText("user_firstNameLabel") + client.getPrenom());
        labelEmail.setText(LangageManager.getInstance().getText("user_mailLabel") + client.getMail());
        labelArgent.setText(LangageManager.getInstance().getText("user_moneyLabel") + client.getArgent());
        addMoneyLabel.setText(LangageManager.getInstance().getText("user_addMoneyLabel"));
        montantComboBox.setPromptText(LangageManager.getInstance().getText("user_selectAmount"));
        boutonAjouter.setText(LangageManager.getInstance().getText("user_addMoneyButton"));
        boutonDeconnexion.setText(LangageManager.getInstance().getText("user_logoutButton"));
    }

    @FXML
    public void handleAjouterArgent(){
        Client client = Connexion.getInstance().getClientConnecte();
        if(montantComboBox.getValue() != null){
            client.ajouterArgent(montantComboBox.getValue());
            new RepositoryClient().mettreAJourArgent(client);
        }
        sceneHandler.mettreAJourHeader();
        changerLangue();
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
