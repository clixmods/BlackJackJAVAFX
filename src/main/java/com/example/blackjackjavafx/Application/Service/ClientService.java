package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.RepositoryClient;

import java.util.Date;
import java.util.List;

public class ClientService {
    private static ClientService INSTANCE;

    private RepositoryClient repository = new RepositoryClient();

    private ClientService() {}

    public static ClientService getInstance() {
        if(INSTANCE == null)
        {
            INSTANCE = new ClientService();
        }
        return INSTANCE;
    }

    public Boolean creerClient(int id,
                               String login,
                               String mail,
                               String nom,
                               String prenom,
                               int argent,
                               String password,
                               Date dateNaissance)
    {
        String passwordHashed = Password.hash(password);

        Client client = new Client(id,login,mail,nom,prenom, argent,passwordHashed,dateNaissance);
        Boolean firstResult = repository.inserer(client);

        return firstResult;
    }

    public List<Client> getClients()
    {
        return repository.recupereTout();
    }

    public Client getClient(int id)
    {
        return repository.recupere(id);
    }

    public Client getClient(String login)
    {
        return repository.recupereBy("login", login);
    }

    public Client getClientByMail(String mail)
    {
        return repository.recupereBy("mail", mail);
    }

    public void supprimeClient(int id)
    {
        repository.supprimer(id);
    }

    public void mettreAJourClient(Client client)
    {
        repository.mettreAJour(client);
    }

}
