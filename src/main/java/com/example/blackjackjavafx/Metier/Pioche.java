package com.example.blackjackjavafx.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pioche {

    private List<Carte> paquet;

    public Pioche(){
        this.paquet = new ArrayList<>();
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de piques", i));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de coeur", i));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de carreau", i));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de trefle", i));
        }
        paquet.add(new Carte("Valet de trefle" , 10));
        paquet.add(new Carte("Roi de trefle", 10));
        paquet.add(new Carte("Reine de trefle" , 10));
        paquet.add(new Carte("As de trefle" , 11));

        paquet.add(new Carte("Valet de carreau" , 10));
        paquet.add(new Carte("Roi de carreau" , 10));
        paquet.add(new Carte("Reine de carreau" , 10));
        paquet.add(new Carte("As de carreau" , 11));

        paquet.add(new Carte("Valet de coeur" , 10));
        paquet.add(new Carte("Roi de coeur" , 10));
        paquet.add(new Carte("Reine de coeur" , 10));
        paquet.add(new Carte("As de coeur" , 11));

        paquet.add(new Carte("Valet de piques" , 10));
        paquet.add(new Carte("Roi de piques" , 10));
        paquet.add(new Carte("Reine de piques" , 10));
        paquet.add(new Carte("As de piques" , 11));
    }

    public void melanger(){
        for (int i = 0; i < 52; i++) {
            int randIndex = i + new Random().nextInt(52 - i);
            Carte temp = paquet.get(i);
            paquet.set(i, paquet.get(randIndex));
            paquet.set(randIndex, temp);
        }
    }

    public Carte piocher(){
        return paquet.remove(0);
    }
}
