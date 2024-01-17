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

        dictionnaire.put("jeu_handDealerText", "Main du croupier : ");
        dictionnaire.put("jeu_handPlayerText", "Main du joueur : ");
        dictionnaire.put("jeu_messageRoundText_selectMise", "Sélectionnez votre mise");
        dictionnaire.put("jeu_messageRoundText_cardDistribution", "Distribution des cartes");
        dictionnaire.put("jeu_messageRoundText_playerTurn", "Tour joueur");
        dictionnaire.put("jeu_messageRoundText_dealerTurn", "Tour croupier");
        dictionnaire.put("jeu_messageRoundText_victory", "Vous avez gagné ! Vous remportez ");
        dictionnaire.put("jeu_messageRoundText_draw", "Égalité !");
        dictionnaire.put("jeu_messageRoundText_lose", "Vous avez perdu");
        dictionnaire.put("jeu_standButton", "Rester");
        dictionnaire.put("jeu_hitButton", "Piocher");
        dictionnaire.put("jeu_doubleButton", "Doubler");
        dictionnaire.put("jeu_restartButton", "Recommencer");

        dictionnaire.put("header_nomCompteLabel_disconnected", "Non connecté");

        dictionnaire.put("accueil_startGameButton", "Démarrer une partie");
        dictionnaire.put("accueil_connectionButton", "Connexion");
        dictionnaire.put("accueil_inscriptionButton", "Inscription");

        dictionnaire.put("inscription_title", "Inscription");
        dictionnaire.put("inscription_messageInfo_inscription", "Inscrivez vous pour rejoindre la plateforme");
        dictionnaire.put("inscription_messageInfo_successful1", "Inscription réussie ");
        dictionnaire.put("inscription_messageInfo_successful2", "Vous allez être redirigé vers la page de connexion.");
        dictionnaire.put("inscription_messageInfo_loginNotAvailable", "Login non disponible");
        dictionnaire.put("inscription_messageInfo_mailNotAvailable", "Email non disponible");
        dictionnaire.put("inscription_messageInfo_passwordNotSecure", "Mot de passe pas assez sécurisé (Minimum 8 caractères, 1 majuscule, 1 chiffre, 1 caractère spécial)");
        dictionnaire.put("inscription_messageInfo_passwordNotEquals", "Les mots de passes ne correspondent pas");
        dictionnaire.put("inscription_messageInfo_namesMissing", "Nom et/ou prénom incomplet");
        dictionnaire.put("inscription_messageInfo_notOfAge", "Vous devez être majeur pour pouvoir vous inscrire");
        dictionnaire.put("inscription_mailFieldDescriptor", "Adresse mail");
        dictionnaire.put("inscription_loginFieldDescriptor", "Login");
        dictionnaire.put("inscription_nameFieldDescriptor", "Nom");
        dictionnaire.put("inscription_firstNameFieldDescriptor","Prénom");
        dictionnaire.put("inscription_birthDateFieldDescriptor", "Date de naissance");
        dictionnaire.put("inscription_passwordFieldDescriptor", "Mot de passe");
        dictionnaire.put("inscription_passwordConfirmationFieldDescriptor", "Confirmer le mot de passe");
        dictionnaire.put("inscription_termsAndConditionsCheckBox", "J'accepte les CGU");
        dictionnaire.put("inscription_submitButton", "S'inscrire");
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
