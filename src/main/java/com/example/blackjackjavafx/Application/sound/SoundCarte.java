package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundCarte implements I_SoundJeu {

    private static SoundCarte instance;
    private MediaPlayer mediaPlayer;

    private SoundCarte() {
        String sonJeton = getClass().getResource("/sound.effects/sound_tirer_carte.mp3").toExternalForm();
        Media media = new Media(sonJeton);
        mediaPlayer = new MediaPlayer(media);
    }

    public static SoundCarte getInstance() {
        if (instance == null) {
            instance = new SoundCarte();
        }
        return instance;
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public void play() {
        mediaPlayer.stop();
        mediaPlayer.play();
    }
}