package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;

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
}
