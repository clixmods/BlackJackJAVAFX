package com.example.blackjackjavafx.Metier;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private int valeurMain;

    private List<Carte> main;

    int nbAs;

    public Participant(){
        valeurMain = 0;
        main = new ArrayList<>();
        nbAs = 0;
    }

    public void calculerValeurMain(){
        //Met à jour la "vraie" valeur de la main, en fonction des As
        while(nbAs !=0 && valeurMain>21){
            valeurMain -= 10;
            nbAs -= 1;
        }
    }

    public int getValeurMain(){
        return valeurMain;
    }

    public Carte piocher(Pioche pioche){
        //Ajoute une carte de la pioche dans la main du joueur, et ajoute sa valeur "brute" à valeurMain
        Carte carte = pioche.piocher();
        if (carte.getValeur() == 11){
            nbAs ++;
        }
        valeurMain += carte.getValeur();
        main.add(carte);
        return carte;
    }
}
