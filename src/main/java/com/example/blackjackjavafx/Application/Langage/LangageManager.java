package com.example.blackjackjavafx.Application.Langage;

public abstract class LangageManager {

    private static Langage INSTANCE = LangageFR.getInstance();

    public static Langage getInstance(){
        return INSTANCE;
    }

    public static void setInstance(Langage langage){
        INSTANCE = langage;
    }
}
