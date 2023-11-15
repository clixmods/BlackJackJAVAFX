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
        int somme = 0;
        for (Carte carte : main){
            somme += carte.getValeur();
        }
        while(nbAs !=0 && somme>21){
            somme -= 10;
            nbAs -= 1;
        }
        valeurMain = somme;
    }

    public int getValeurMain(){
        return valeurMain;
    }

    public void piocher(Pioche pioche){
        Carte carte = pioche.piocher();
        if (carte.getValeur() == 11){
            nbAs ++;
        }
        main.add(carte);
    }
}
