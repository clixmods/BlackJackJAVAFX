package com.example.blackjackjavafx.Metier;

public class Client {

    private String nom;

    private int argent;

    public Client(String nom){
        this.nom = nom;
        this.argent = 0;
    }

    public int getArgent(){
        return argent;
    }

    public void ajouterArgent(int somme){
        argent += somme;
    }
}
