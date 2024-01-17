package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundVictoire extends Abstract_SoundJeu {

    private MediaPlayer mediaPlayer;

    public SoundVictoire() {
        String sonJeton = getClass().getResource("/sound.effects/sound_victory.mp3").toExternalForm();
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