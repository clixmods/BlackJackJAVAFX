package com.example.blackjackjavafx.Application.Langage;

import java.util.HashMap;
import java.util.Map;

public class LangageEN implements Langage{

    private Map<String, String> dictionnaire;

    private static LangageEN INSTANCE;

    private LangageEN(){
        dictionnaire = new HashMap<>();

        dictionnaire.put("connexion_loginFieldDescriptor", "Login");
        dictionnaire.put("connexion_passwordFieldDescriptor", "Password");
        dictionnaire.put("connexion_connexionButton", "Connection");
        dictionnaire.put("connexion_retourButton", "Back");
        dictionnaire.put("connexion_textInfo_tryConnect", "Try to connect");
        dictionnaire.put("connexion_textInfo_successfulConnection", "Successful connection");
        dictionnaire.put("connexion_textInfo_incorrectPassword", "Incorrect password");
        dictionnaire.put("connexion_textInfo_missingLogin", "This login does not exist");
        dictionnaire.put("connexion_textInfo_unknown_error", "Unknown error");

        dictionnaire.put("mise_argentText", "Your money : ");
        dictionnaire.put("mise_miseText_select", "Select your bet");
        dictionnaire.put("mise_miseText_playersBet", "Your bet : ");
        dictionnaire.put("mise_miseText_cantBet", "You can't play if you don't bet.");
        dictionnaire.put("mise_miseText_notEnoughMoney", "You no longer have enough money to bet so much.");
        dictionnaire.put("mise_validerMiseButton", "I validate my bet");

        dictionnaire.put("jeu_handDealerText", "Dealer's hand : ");
        dictionnaire.put("jeu_handPlayerText", "Player's hand : ");
        dictionnaire.put("jeu_messageRoundText_selectMise", "Select your bet");
        dictionnaire.put("jeu_messageRoundText_cardDistribution", "Card distribution");
        dictionnaire.put("jeu_messageRoundText_playerTurn", "Player's turn");
        dictionnaire.put("jeu_messageRoundText_dealerTurn", "Dealer's turn");
        dictionnaire.put("jeu_messageRoundText_victory", "You've won! You win ");
        dictionnaire.put("jeu_messageRoundText_draw", "Draw !");
        dictionnaire.put("jeu_messageRoundText_lose", "You've lost");
        dictionnaire.put("jeu_standButton", "Stay");
        dictionnaire.put("jeu_hitButton", "Hit");
        dictionnaire.put("jeu_doubleButton", "Double");
        dictionnaire.put("jeu_restartButton", "Restart");
    }

    public static LangageEN getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LangageEN();
        }
        return INSTANCE;
    }

    @Override
    public String getText(String key) {
        return dictionnaire.get(key);
    }
}
