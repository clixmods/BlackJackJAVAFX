package com.example.blackjackjavafx.Application.Service;

import com.example.blackjackjavafx.Application.lib.Password;
import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.ClientDetail;
import com.example.blackjackjavafx.Repository.RepositoryClient;
import com.example.blackjackjavafx.Repository.RepositoryClientDetail;

import java.util.Date;
import java.util.List;

public class ClientService {
    private static ClientService INSTANCE;

    private RepositoryClient repository = new RepositoryClient();

    private RepositoryClientDetail repositoryClientDetail = new RepositoryClientDetail();

    private ClientService() {}

    public static ClientService getInstance() {
        if(INSTANCE == null)
        {
            INSTANCE = new ClientService();
        }
        return INSTANCE;
    }

    public Boolean creerClient(int id, String login, String mail, String nom, String prenom, int argent, String password,
                            String adresse, int codePostal, String ville, Date dateNaissance, Date dateInscription, String telephone)
    {
        String passwordHashed = Password.hash(password);

        Client client = new Client(id,login,mail,nom,prenom, argent,passwordHashed);
        Boolean firstResult = repository.inserer(client);
        Boolean secondResult = false;
        Client clientDansBaseDeDonnee = getClient(login);
        if(clientDansBaseDeDonnee != null)
        {
            ClientDetail clientDetail = new ClientDetail(clientDansBaseDeDonnee.getId(), adresse,codePostal,ville,dateNaissance,dateInscription,telephone);
            secondResult = repositoryClientDetail.inserer(clientDetail);
        }

        return firstResult && secondResult;
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