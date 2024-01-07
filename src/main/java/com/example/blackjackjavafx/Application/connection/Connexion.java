package com.example.blackjackjavafx.Application.connection;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Metier.Client;

import java.util.Objects;

public class Connexion
{
    private static Client loggedClient;
    private static Connexion connexion;
    public static Connexion getInstance() {
        if(connexion == null)
        {
            connexion = new Connexion();
        }
        return connexion;
    }
    private Connexion(){};

    public ConnexionResult tryConnect(String login, String password)
    {
        Client client = ClientService.getInstance().getClient(login);
        if(client == null)
        {
            return ConnexionResult.UNDEFINED_LOGIN;
        }

        if(Password.verify(password,client.getPassword()))
        {
            loggedClient = client;
            return ConnexionResult.SUCCESS;
        }
        else
        {
            return ConnexionResult.BAD_PASSWORD;
        }

    }


}
