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

        dictionnaire.put("header_nomCompteLabel_disconnected", "Not connected");

        dictionnaire.put("accueil_startGameButton", "Start a game");
        dictionnaire.put("accueil_connectionButton", "Login");
        dictionnaire.put("accueil_inscriptionButton", "Sign up");
        dictionnaire.put("accueil_rulesButton", "Rules");
        dictionnaire.put("accueil_termsAndConditionsButton", "Terms and Conditions");

        dictionnaire.put("inscription_title", "Inscription");
        dictionnaire.put("inscription_messageInfo_inscription", "Register to join the platform");
        dictionnaire.put("inscription_messageInfo_successful1", "Successful registration ");
        dictionnaire.put("inscription_messageInfo_successful2", "You will be redirected to the login page.");
        dictionnaire.put("inscription_messageInfo_loginNotAvailable", "Login not available");
        dictionnaire.put("inscription_messageInfo_mailNotAvailable", "Email not available");
        dictionnaire.put("inscription_messageInfo_passwordNotSecure", "Password not secure enough (Minimum 8 characters, 1 uppercase, 1 number, 1 special character)");
        dictionnaire.put("inscription_messageInfo_passwordNotEquals", "Passwords don't match");
        dictionnaire.put("inscription_messageInfo_namesMissing", "Incomplete first and/or last name");
        dictionnaire.put("inscription_messageInfo_notOfAge", "You must be 18 or over to register");
        dictionnaire.put("inscription_mailFieldDescriptor", "E-mail address");
        dictionnaire.put("inscription_loginFieldDescriptor", "Login");
        dictionnaire.put("inscription_nameFieldDescriptor", "Name");
        dictionnaire.put("inscription_firstNameFieldDescriptor","First name");
        dictionnaire.put("inscription_birthDateFieldDescriptor", "Birth Date");
        dictionnaire.put("inscription_passwordFieldDescriptor", "Password");
        dictionnaire.put("inscription_passwordConfirmationFieldDescriptor", "Confirm password");
        dictionnaire.put("inscription_termsAndConditionsCheckBox", "I accept the terms and conditions of use");
        dictionnaire.put("inscription_submitButton", "Register");

        dictionnaire.put("regles_label1", "The player has several options, including Remain, Shoot and Double.");
        dictionnaire.put("regles_label2", "Stay : The player is satisfied with their current hand and does not wish to receive any new cards. They now waits for the dealer to play his hand.");
        dictionnaire.put("regles_label3", "Hit: The player wishes to receive a new card to increase the value of their hand. They may continue to draw cards until they reaches a satisfactory value or exceeds 21.");
        dictionnaire.put("regles_label4", "Double : The player doubles they initial bet and receives an additional card. They can't draw a card after that.");
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
