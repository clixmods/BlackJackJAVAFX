package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;

import java.util.Date;
import java.util.regex.Pattern;

public class InscriptionHelper
{
    public static boolean isLoginAvailable(String login)
    {
        // Todo : Requete trop lourde pour check, il faudrait faire une requete dédier au lieu de recuperer le client entier
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClient(login);
        Boolean condition = client == null;
        if(condition)
        {
            System.out.println("Inscription : Login disponible");
        }
        return condition;
    }

    public static boolean isMailAvailable(String mail) {
        // Todo : Requete trop lourde pour check, il faudrait faire une requete dédier au lieu de recuperer le client entier
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClientByMail(mail);

        Boolean condition = client == null;
        if(condition)
        {
            System.out.println("Inscription : Mail disponible");
        }
        return condition;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifie si le numéro de téléphone n'est pas nul et a une longueur minimale
        if (phoneNumber == null || phoneNumber.length() < 10) {
            System.out.println("Numero telephone invalide");
            return false;
        }

        // Vérifie si le numéro de téléphone contient uniquement des chiffres
        if (!isNumeric(phoneNumber))
        {
            System.out.println("Numero telephone n'est pas numerique");
            return false;
        }

        return true;
    }

    private static boolean isNumeric(String str) {
        // Vérifie si la chaîne est composée uniquement de chiffres
        return Pattern.compile("\\d+").matcher(str).matches();
    }


    public static int calculerAge(Date dateNaissance) {
        Date currentDate = new Date();
        return  currentDate.getYear() - dateNaissance.getYear();

    }
    public static boolean isMajor(Date dateNaissance) {
        return calculerAge(dateNaissance) > 18;
    }

}
