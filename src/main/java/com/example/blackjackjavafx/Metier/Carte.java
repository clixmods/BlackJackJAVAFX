package com.example.blackjackjavafx.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Carte {

    private String nom;

    private int valeur;

    private String imageURL;

    public Carte(String nom, int valeur, String imageURL){
        this.nom = nom;
        this.valeur = valeur;
        this.imageURL = imageURL;
    }

    public int getValeur(){
        return valeur;
    }
    
    public ImageView getImageView(){
        InputStream inputStream = getClass().getResourceAsStream(this.imageURL);
        if(inputStream != null)
        {
            Image cardImage = new Image(inputStream);
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(100); // Ajustez la largeur de la carte
            cardImageView.setPreserveRatio(true);
            cardImageView.setSmooth(true);
            return cardImageView;
        }
        return null;
    }
}
