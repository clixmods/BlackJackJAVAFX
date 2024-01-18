package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.Service.ClientService;
import com.example.blackjackjavafx.Metier.Client;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class InscriptionHelper
{
    public static boolean isLoginAvailable(String login)
    {
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClient(login);
        Boolean condition = client == null;
        return condition;
    }

    public static boolean isMailAvailable(String mail) {
        ClientService clientService = ClientService.getInstance();
        Client client = clientService.getClientByMail(mail);

        Boolean condition = client == null;
        return condition;
    }

    public static int calculerAge(LocalDate dateNaissance) {
        LocalDate currentDate = LocalDate.now();
        Period difference = Period.between(dateNaissance, currentDate);
        return difference.getYears();
    }
    public static boolean isMajor(LocalDate dateNaissance) {
        return calculerAge(dateNaissance) >= 18;
    }

}
