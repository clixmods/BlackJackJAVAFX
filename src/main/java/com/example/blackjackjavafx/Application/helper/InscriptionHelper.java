package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;

import java.util.regex.Pattern;

public class InscriptionHelper
{
    public static boolean isLoginAvailable(String login)
    {
        // Todo : Requete trop lourde pour check, il faudrait faire une requete dédier au lieu de recuperer le client entier
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClient(login);
        return client == null;
    }

    public static boolean isMailAvailable(String mail) {
        // Todo : Requete trop lourde pour check, il faudrait faire une requete dédier au lieu de recuperer le client entier
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClientByMail(mail);
        return client == null;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifie si le numéro de téléphone n'est pas nul et a une longueur minimale
        if (phoneNumber == null || phoneNumber.length() < 10) {
            return false;
        }

        // Vérifie si le numéro de téléphone contient uniquement des chiffres
        if (!isNumeric(phoneNumber)) {
            return false;
        }

        return true;
    }

    private static boolean isNumeric(String str) {
        // Vérifie si la chaîne est composée uniquement de chiffres
        return Pattern.compile("\\d+").matcher(str).matches();
    }


}
