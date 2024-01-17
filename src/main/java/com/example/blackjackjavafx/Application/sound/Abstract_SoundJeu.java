package com.example.blackjackjavafx.Application.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Abstract_SoundJeu {
    private MediaPlayer mediaPlayer;

    public abstract void play(double volume);
    public abstract void setVolume(double volume);
}
