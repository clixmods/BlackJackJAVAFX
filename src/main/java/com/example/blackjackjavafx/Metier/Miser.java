package com.example.blackjackjavafx.Metier;

import com.example.blackjackjavafx.Application.controller.ControleurMise;

import java.util.ArrayList;
import java.util.List;

public class Miser {

    private List<Jeton> listeJetons;

    private int mise;

    private ControleurMise controleurMise;

    private Client client;

    public Miser(Client client, ControleurMise controleurMise){
        mise = 0;
        this.client = client;
        this.controleurMise = controleurMise;
        listeJetons = new ArrayList<>();
        listeJetons.add(new Jeton(500, "/images.jeton/jeton_500.png"));
        listeJetons.add(new Jeton(100, "/images.jeton/jeton_100.png"));
        listeJetons.add(new Jeton(50, "/images.jeton/jeton_50.png"));
        listeJetons.add(new Jeton(20, "/images.jeton/jeton_20.png"));
        listeJetons.add(new Jeton(1, "/images.jeton/jeton_1.png"));
    }

    public int getMise(){
        return mise;
    }

    public ArrayList<Jeton> obtenirJetons(){
        ArrayList<Jeton> jetons = new ArrayList<>();
        for (int i = 0; i<listeJetons.size(); i++){
            if (client.getArgent()>=listeJetons.get(i).getValeur()){
                for (int j = i; j<listeJetons.size(); j++){
                    jetons.add(listeJetons.get(j));
                }
                break;
            }
        }
        return jetons;
    }

    public void ajoutMise(int ajout){
        mise += ajout;
        client.retirerArgent(ajout);
        controleurMise.mettreAJourAffichage();
    }

    public void annulerMise(){
        client.ajouterArgent(mise);
        mise = 0;
    }

    public int validerMise(){
        return mise;
    }
}
