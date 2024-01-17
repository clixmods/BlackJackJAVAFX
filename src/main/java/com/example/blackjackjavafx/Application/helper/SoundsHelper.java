package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.sound.SoundBlackJack;
import com.example.blackjackjavafx.Application.sound.SoundCarte;
import com.example.blackjackjavafx.Application.sound.SoundJeton;
import com.example.blackjackjavafx.Application.sound.SoundVictoire;

public class SoundsHelper {
    private static SoundsHelper instance;
    private SoundJeton soundJeton;
    private SoundCarte soundCarte;
    private SoundVictoire soundVictoire;
    private SoundBlackJack soundBlackJack;

    private SoundsHelper(){
        soundCarte = SoundCarte.getInstance();
        soundVictoire = SoundVictoire.getInstance();
        soundBlackJack = SoundBlackJack.getInstance();
        soundJeton = SoundJeton.getInstance();
    }

    public static SoundsHelper getInstance() {
        if (instance == null) {
            instance = new SoundsHelper();
        }
        return instance;
    }

    public void setVolume(double volume){
        SoundJeton.getInstance().setVolume(volume);
        SoundCarte.getInstance().setVolume(volume);
        SoundVictoire.getInstance().setVolume(volume);
        SoundBlackJack.getInstance().setVolume(volume);
    }

    public void jouerSonCarte(){
        soundCarte.play();
    }

    public void jouerSonJeton(){
        soundJeton.play();
    }

    public void jouerSonVictoire(){
        soundVictoire.play();
    }

    public void jouerSonBlackJack(){
        soundBlackJack.play();
    }
}
