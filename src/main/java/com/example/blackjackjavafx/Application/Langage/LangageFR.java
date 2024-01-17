package com.example.blackjackjavafx.Application.Langage;

import java.util.HashMap;
import java.util.Map;

public class LangageFR implements Langage{

    private Map<String, String> dictionnaire;

    private static LangageFR INSTANCE;

    private LangageFR(){
        dictionnaire = new HashMap<>();

        dictionnaire.put("connexion_loginFieldDescriptor", "Login");
        dictionnaire.put("connexion_passwordFieldDescriptor", "Mot de passe");
        dictionnaire.put("connexion_connexionButton", "Connexion");
        dictionnaire.put("connexion_retourButton", "Retour");
        dictionnaire.put("connexion_textInfo_tryConnect", "Essayez de vous connecter");
        dictionnaire.put("connexion_textInfo_successfulConnection", "Connexion réussie");
        dictionnaire.put("connexion_textInfo_incorrectPassword", "Mot de passe incorrect");
        dictionnaire.put("connexion_textInfo_missingLogin", "Login inexistant");
        dictionnaire.put("connexion_textInfo_unknown_error", "Erreur inconnue");

        dictionnaire.put("mise_argentText", "Votre argent : ");
        dictionnaire.put("mise_miseText_select", "Sélectionnez votre mise");
        dictionnaire.put("mise_miseText_playersBet", "Votre mise : ");
        dictionnaire.put("mise_miseText_cantBet", "Vous ne pouvez jouer si vous ne misez pas.");
        dictionnaire.put("mise_miseText_notEnoughMoney", "Vous n'avez plus assez d'argent pour miser autant.");
        dictionnaire.put("mise_validerMiseButton", "Je valide ma mise");
    }

    public static LangageFR getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LangageFR();
        }
        return INSTANCE;
    }

    @Override
    public String getText(String key) {
        return dictionnaire.get(key);
    }
}
