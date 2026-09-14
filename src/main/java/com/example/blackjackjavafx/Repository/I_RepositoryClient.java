package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;

public interface I_RepositoryClient extends I_Repository<Client> {

    Client recupereBy(String nomColonne, String valeur);

    void mettreAJourArgent(Client client);
}
