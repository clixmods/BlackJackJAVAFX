package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.RepositoryClient;

import java.util.List;

public class ClientService {
    private final static ClientService INSTANCE = new ClientService();

    private RepositoryClient repository = new RepositoryClient();

    private ClientService() {}

    public static ClientService getInstance() {
        return INSTANCE;
    }

    public void creerClient(String nom, int argent)
    {
        Client client = new Client(-1,nom, resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getDate("dateNaissance"), resultSet.getDate("dateInscription"), resultSet.getString("telephone"), argent, resultSet.getString("adresse"), resultSet.getInt("codepostal"), resultSet.getString("ville"), resultSet.getString("pays"), resultSet.getString("password"));
        repository.inserer(client);
    }

    public List<Client> getClients()
    {
        return repository.recupereTout();
    }

    public Client getClient(int id)
    {
        return repository.recupere(id);
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
