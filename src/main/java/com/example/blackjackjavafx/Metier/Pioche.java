package com.example.blackjackjavafx.Metier;

import com.example.blackjackjavafx.card.Card;
import com.example.blackjackjavafx.card.CardAs;
import com.example.blackjackjavafx.card.CardBuche;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pioche {

    private List<Carte> paquet;

    public Pioche(){
        this.paquet = new ArrayList<>();
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de Piques", i , "/images/cards/"+i+"_of_spades.png"));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de Coeur", i , "/images/cards/"+i+"_of_hearts.png"));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de Carreau", i , "/images/cards/"+i+"_of_diamonds.png"));
        }
        for(int i = 2 ; i <= 10 ; i++)
        {
            paquet.add(new Carte(i+" de Trefle", i , "/images/cards/"+i+"_of_clubs.png"));
        }

        paquet.add(new Carte("Valet de Trefle" , 10, "/images/cards/jack_of_clubs2.png"));
        paquet.add(new Carte("Roi de Trefle", 10, "/images/cards/king_of_clubs2.png"));
        paquet.add(new Carte("Reine de Trefle" , 10, "/images/cards/queen_of_clubs2.png"));
        paquet.add(new Carte("As de Trefle" , 11, "/images/cards/ace_of_clubs.png"));

        paquet.add(new Carte("Valet de Carreau" , 10, "/images/cards/jack_of_diamonds2.png"));
        paquet.add(new Carte("Roi de Carreau" , 10, "/images/cards/king_of_diamonds2.png"));
        paquet.add(new Carte("Reine de Carreau" , 10, "/images/cards/queen_of_diamonds2.png"));
        paquet.add(new Carte("As de Carreau" , 11, "/images/cards/ace_of_diamonds.png"));

        paquet.add(new Carte("Valet de coeur" , 10, "/images/cards/jack_of_hearts2.png"));
        paquet.add(new Carte("Roi de coeur" , 10, "/images/cards/king_of_hearts2.png"));
        paquet.add(new Carte("Reine de coeur" , 10, "/images/cards/queen_of_hearts2.png"));
        paquet.add(new Carte("As de coeur" , 11, "/images/cards/ace_of_hearts.png"));

        paquet.add(new Carte("Valet de piques" , 10, "/images/cards/jack_of_spades2.png"));
        paquet.add(new Carte("Roi de piques" , 10, "/images/cards/king_of_spades2.png"));
        paquet.add(new Carte("Reine de piques" , 10, "/images/cards/queen_of_spades2.png"));
        paquet.add(new Carte("As de piques" , 11, "/images/cards/ace_of_spades.png"));
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
