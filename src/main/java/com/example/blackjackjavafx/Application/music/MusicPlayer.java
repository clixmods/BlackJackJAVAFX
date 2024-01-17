package com.example.blackjackjavafx.Application.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
    MediaPlayer mediaPlayer;
    public MusicPlayer(){

        // On importe la musique et on la boucle
        try {
            String musiqueAmbiance = getClass().getResource("/jazz_libre_de_droits.mp3").toExternalForm();
            Media media = new Media(musiqueAmbiance);
            mediaPlayer = new MediaPlayer(media);
        }
        catch(NullPointerException e){
            System.err.println("Erreur musique : " + e.getMessage());
        }
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        reglerVolumeMusique(0.5);
        mediaPlayer.play();
    }

    public void reglerVolumeMusique(double volume){
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
        if(volume == 0.0){
            mediaPlayer.pause();
        }
    }
}
