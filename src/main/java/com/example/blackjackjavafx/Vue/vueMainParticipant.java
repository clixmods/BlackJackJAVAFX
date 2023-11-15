package com.example.blackjackjavafx.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;

public class vueMainParticipant extends HBox {

    public vueMainParticipant(){
        super();
    }

    public void ajouterCarte(String imageCarte){
        InputStream inputStream = getClass().getResourceAsStream(imageCarte);
        if(inputStream != null)
        {
            Image cardImage = new Image(inputStream);
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(100); // Ajustez la largeur de la carte
            cardImageView.setPreserveRatio(true);
            cardImageView.setSmooth(true);
            this.getChildren().add(cardImageView);
        }
    }
}
