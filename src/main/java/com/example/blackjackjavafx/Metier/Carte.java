package com.example.blackjackjavafx.Metier;

public class Carte {

    private String nom;

    private int valeur;

    private String imageURL;

    public Carte(String nom, int valeur, String imageURL){
        this.nom = nom;
        this.valeur = valeur;
        this.imageURL = imageURL;
    }

    public String getNom(){
        return nom;
    }

    public int getValeur(){
        return valeur;
    }
}
