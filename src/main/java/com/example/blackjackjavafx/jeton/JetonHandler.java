package com.example.blackjackjavafx.jeton;

import java.util.ArrayList;
import java.util.List;


/**
 * Le gestionnaire des jetons
 */
public class JetonHandler {

    public List<Jeton> jetons = new ArrayList<>();
    public List<Jeton> baseJetons = new ArrayList<>();
    public JetonHandler()
    {
        baseJetons.add(new Jeton(500, "/images.jeton/jeton_blue.png"));
        baseJetons.add(new Jeton(100, "/images.jeton/jeton_green.png"));
        baseJetons.add(new Jeton(50, "/images.jeton/jeton_purple.png"));
        baseJetons.add(new Jeton(20, "/images.jeton/jeton_red.png"));
        baseJetons.add(new Jeton(1, "/images.jeton/jeton_yellow.png"));
    }

    /**
     * Cette méthode permet de gérer les jetons disponibles par rapport à la somme d'argent donnée en parametre
     * @param moneyToConvert
     */
    public void UpdateJetonFromMoney(int moneyToConvert)
    {
        jetons.clear();
        for (int i = 0 ; i < baseJetons.size(); i++)
        {
            GenerateJeton(moneyToConvert, baseJetons.get(i));
        }
    }

    public void GenerateJeton(int moneyToConvert,Jeton jetonToAdd)
    {
        for(int i = 0; i < moneyToConvert / jetonToAdd.get_value() ; i++)
        {
            jetons.add(jetonToAdd);
        }
    }
}
