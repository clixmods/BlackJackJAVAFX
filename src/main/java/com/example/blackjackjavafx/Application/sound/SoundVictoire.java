package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundVictoire implements I_SoundJeu {

    private static SoundVictoire instance;
    private MediaPlayer mediaPlayer;

    private SoundVictoire() {
        String sonJeton = getClass().getResource("/sound.effects/sound_victory.mp3").toExternalForm();
        Media media = new Media(sonJeton);
        mediaPlayer = new MediaPlayer(media);
    }

    public static SoundVictoire getInstance() {
        if (instance == null) {
            instance = new SoundVictoire();
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