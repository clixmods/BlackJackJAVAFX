package com.example.blackjackjavafx.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class Jeton {

    private int valeur;
    private String imageURL;
    public Jeton(int valeur, String imageURL)
    {
        this.valeur = valeur;
        this.imageURL = imageURL;
    }

    public ImageView getImageView(){
        InputStream inputStream = getClass().getResourceAsStream(this.imageURL);
        if(inputStream != null)
        {
            Image jetonImage = new Image(inputStream);
            ImageView jetonImageView = new ImageView(jetonImage);
            jetonImageView.setFitWidth(100); // Ajustez la largeur du jeton
            jetonImageView.setPreserveRatio(true);
            jetonImageView.setSmooth(true);
            return jetonImageView;
        }
        return null;
    }

    public int getValeur(){
        return valeur;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
