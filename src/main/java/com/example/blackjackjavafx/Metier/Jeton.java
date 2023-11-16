package com.example.blackjackjavafx.Metier;

public class Jeton {

        private int valeur;
    private String imageURL;
    public Jeton(int valeur, String imageURL)
    {
        this.valeur = valeur;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getValeur(){
        return valeur;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
