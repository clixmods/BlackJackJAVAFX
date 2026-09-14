package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.I_RepositoryClient;
import com.example.blackjackjavafx.Repository.RepositoryClient;
import com.example.blackjackjavafx.Repository.RepositoryClientMemoire;
import com.example.blackjackjavafx.Repository.Sql.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ClientService {
    public static final String LOGIN_INVITE = "invite";
    private static final int ARGENT_INVITE = 1000;

    private static ClientService INSTANCE;

    private final I_RepositoryClient repository;

    private final boolean horsLigne;

    private ClientService() {
        // Sans fichier de configuration, l'application fonctionne sans base de données
        horsLigne = !Configuration.estPresente();
        if (horsLigne) {
            repository = new RepositoryClientMemoire();
            creerClient(LOGIN_INVITE, "invite@blackjack.local", "Invité", "", ARGENT_INVITE,
                    UUID.randomUUID().toString(), LocalDate.of(2000, 1, 1));
        }
        else {
            repository = new RepositoryClient();
        }
    }

    public static ClientService getInstance() {
        if(INSTANCE == null)
        {
            INSTANCE = new ClientService();
        }
        return INSTANCE;
    }

    public boolean estHorsLigne() {
        return horsLigne;
    }

    public Boolean creerClient(String login,
                               String mail,
                               String nom,
                               String prenom,
                               int argent,
                               String password,
                               LocalDate dateNaissance)
    {
        String passwordHashed = Password.hash(password);

        Client client = new Client(login,mail,nom,prenom, argent,passwordHashed,dateNaissance);
        Boolean firstResult = repository.inserer(client);

        return firstResult;
    }

    public List<Client> getClients()
    {
        return repository.recupereTout();
    }

    public Client getClient(String login)
    {
        return repository.recupereBy("login", login);
    }

    public Client getClientByMail(String mail)
    {
        return repository.recupereBy("mail", mail);
    }

    public void supprimeClient(String login)
    {
        repository.supprimer(login);
    }

    public void mettreAJourClient(Client client)
    {
        repository.mettreAJour(client);
    }

    public void mettreAJourArgentClient(Client client){
        repository.mettreAJourArgent(client);
    }

}
