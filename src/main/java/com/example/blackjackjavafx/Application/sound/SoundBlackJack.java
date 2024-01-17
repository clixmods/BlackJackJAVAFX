package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundBlackJack implements I_SoundJeu {

    private static SoundBlackJack instance;
    private MediaPlayer mediaPlayer;

    private SoundBlackJack() {
        String sonJeton = getClass().getResource("/sound.effects/sound_tirer_carte.mp3").toExternalForm();
        Media media = new Media(sonJeton);
        mediaPlayer = new MediaPlayer(media);
    }

    public static SoundBlackJack getInstance() {
        if (instance == null) {
            instance = new SoundBlackJack();
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