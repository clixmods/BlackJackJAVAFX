package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.sound.SoundBlackJack;
import com.example.blackjackjavafx.Application.sound.SoundCarte;
import com.example.blackjackjavafx.Application.sound.SoundJeton;
import com.example.blackjackjavafx.Application.sound.SoundVictoire;

public class SoundsHelper {
    private static double volume = 0.5;

    public static void setVolume(double v){
        volume = v;
    }

    public static double getVolume(){
        return volume;
    }

}
