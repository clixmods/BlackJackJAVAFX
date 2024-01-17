package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundJeton implements I_SoundJeu {

    private static SoundJeton instance;
    private MediaPlayer mediaPlayer;

    private SoundJeton() {
        String sonJeton = getClass().getResource("/sound.effects/sound_jeton.mp3").toExternalForm();
        Media media = new Media(sonJeton);
        mediaPlayer = new MediaPlayer(media);
    }

    public static SoundJeton getInstance() {
        if (instance == null) {
            instance = new SoundJeton();
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