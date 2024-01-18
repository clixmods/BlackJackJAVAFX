package com.example.blackjackjavafx.Application.controller;

import com.example.blackjackjavafx.Application.Langage.LangageManager;
import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.helper.SoundsHelper;
import com.example.blackjackjavafx.Application.sound.SoundCarte;
import com.example.blackjackjavafx.Application.sound.SoundJeton;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.Jeton;
import com.example.blackjackjavafx.Metier.Miser;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class ControleurMise implements Controleur{

    private class ButtonJetonData
    {
        public Button button;
        public Jeton jeton;

        public ButtonJetonData(Button button , Jeton jeton)
        {
            this.button = button;
            this.jeton = jeton;
        }
    }

    private Client client;
    private int argentJoueur;
    private SceneHandler sceneHandler;
    private Miser miser;

    @FXML
    private Label argentJoueurText;
    @FXML
    private Button buttonValidMise;
    @FXML
    private HBox hBoxJetons;
    @FXML
    private Label miseJoueurText;

    private String miseJoueurTextState;

    private SoundJeton sonJeton;

    private List<ButtonJetonData> buttonsJeton;

    public void creerMise(Client client, SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
        this.sonJeton = new SoundJeton();
        miseJoueurTextState = "mise_miseText_select";
        changerLangue();
        miser = new Miser(client, this);
        argentJoueur = client.getArgent();
        this.client = client;

        // On initialise les jetons ici au lieu de le faire à chaque clique
        // A executer avant de mettre a jour l'affichage
        genererJetons();

        mettreAJourAffichage();



    }

    public void changerLangue(){
        argentJoueurText.setText(LangageManager.getInstance().getText("mise_argentText") + argentJoueur + " $");
        buttonValidMise.setText(LangageManager.getInstance().getText("mise_validerMiseButton"));
        if (miseJoueurTextState.equals("mise_miseText_playersBet")){
            miseJoueurText.setText(LangageManager.getInstance().getText("mise_miseText_playersBet") + miser.getMise());
        }
        else {
            miseJoueurText.setText(LangageManager.getInstance().getText(miseJoueurTextState));
        }
    }

    private void genererJetons(){
        hBoxJetons.getChildren().clear();

        buttonsJeton = new ArrayList<>();

        int index = 0;
        for (Jeton jeton : miser.obtenirJetons()){

            // On genere le bouton
            Button boutonJeton = new Button();
            boutonJeton.maxWidth(100);
            boutonJeton.maxHeight(100);
            boutonJeton.setGraphic(jeton.getImageView());
            boutonJeton.setStyle(
                    "-fx-background-radius: 140em; " +
                    "-fx-min-width: 82px; " +
                    "-fx-min-height: 82px; " +
                    "-fx-max-width: 82; " +
                    "-fx-max-height: 82px;");
            boutonJeton.setPadding(Insets.EMPTY);

            // On set laction du bouton
            boutonJeton.setOnAction(actionEvent -> {
                argentJoueur -= jeton.getValeur();
                miser.ajoutMise(jeton.getValeur());
            });

            // On conserve le bouton dans une liste
            buttonsJeton.add( new ButtonJetonData(boutonJeton, miser.obtenirJetons().get(index)));
            index++;

            // On ajoute le bouton au HBOX
            hBoxJetons.getChildren().add(boutonJeton);
        }
    }

    public void mettreAJourAffichage(){
        mettreAJourJetons();

        miseJoueurText.setText(LangageManager.getInstance().getText("mise_miseText_playersBet") + miser.getMise());
        miseJoueurTextState = "mise_miseText_playersBet";
        argentJoueurText.setText(LangageManager.getInstance().getText("mise_argentText") + argentJoueur + " $");

        jouerSonJeton();
    }

    private void mettreAJourJetons()
    {
        for (int i = 0; i < buttonsJeton.size(); i++)
        {
            buttonsJeton.get(i).button.setVisible(miser.obtenirJetons().contains(buttonsJeton.get(i).jeton));
        }
        hBoxJetons.setVisible(true);
    }

    public void onValidButtonClick(){
        if (miser.getMise() > 0) {
            if (miser.getMise()>ClientService.getInstance().getClient(client.getLogin()).getArgent()){
                miseJoueurText.setText(LangageManager.getInstance().getText("mise_miseText_notEnoughMoney"));
            }
            else {
                sceneHandler.commencerPartie(client, miser.getMise());
                ClientService.getInstance().mettreAJourArgentClient(client);
            }
            sceneHandler.mettreAJourHeader();
        }
        else {
            miseJoueurText.setText(LangageManager.getInstance().getText("mise_miseText_cantBet"));
            miseJoueurTextState = "mise_miseText_cantBet";
        }
    }

    public void onAjoutezArgentButtonClick(){

    }

    public void jouerSonJeton(){
        sonJeton.play(SoundsHelper.getVolume());
    }
}
