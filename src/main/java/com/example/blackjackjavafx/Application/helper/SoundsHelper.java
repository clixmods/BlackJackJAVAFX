package com.example.blackjackjavafx.Application.helper;

public class SoundsHelper {
    private static double volume = 0.5;

    public static void setVolume(double v){
        volume = v;
    }

    public static double getVolume(){
        return volume;
    }

}
