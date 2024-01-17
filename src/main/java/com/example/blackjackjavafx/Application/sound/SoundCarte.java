package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundCarte extends Abstract_SoundJeu {

    private MediaPlayer mediaPlayer;

    public SoundCarte() {
        String sonJeton = getClass().getResource("/sound.effects/sound_tirer_carte.mp3").toExternalForm();
        Media media = new Media(sonJeton);
        mediaPlayer = new MediaPlayer(media);
    }

    public void play(double volume){
        mediaPlayer.stop();
        setVolume(volume);
        mediaPlayer.play();
    }
    public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

}