package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Stockage des clients en mémoire, utilisé en mode hors ligne (sans base de données).
 * Les données sont perdues à la fermeture de l'application.
 * Comme une base de données, il renvoie des copies : modifier un client récupéré
 * ne change rien tant qu'il n'est pas explicitement mis à jour.
 */
public class RepositoryClientMemoire implements I_RepositoryClient {

    private final Map<String, Client> clients = new LinkedHashMap<>();

    @Override
    public Boolean inserer(Client client) {
        if (clients.containsKey(client.getLogin())) {
            return false;
        }
        clients.put(client.getLogin(), copier(client));
        return true;
    }

    @Override
    public void mettreAJour(Client client) {
        clients.replace(client.getLogin(), copier(client));
    }

    @Override
    public void mettreAJourArgent(Client client) {
        // Seul l'argent est mis à jour, comme la requête SQL équivalente
        clients.computeIfPresent(client.getLogin(), (login, stocke) ->
                new Client(stocke.getLogin(), stocke.getMail(), stocke.getNom(), stocke.getPrenom(),
                        client.getArgent(), stocke.getPassword(), stocke.getDateNaissance()));
    }

    @Override
    public void supprimer(String login) {
        clients.remove(login);
    }

    @Override
    public Client recupere(int id) {
        throw new UnsupportedOperationException("Les clients sont identifiés par leur login");
    }

    @Override
    public Client recupereBy(String nomColonne, String valeur) {
        for (Client client : clients.values()) {
            String valeurClient = switch (nomColonne) {
                case "login" -> client.getLogin();
                case "mail" -> client.getMail();
                default -> throw new IllegalArgumentException("Colonne inconnue : " + nomColonne);
            };
            if (valeurClient.equals(valeur)) {
                return copier(client);
            }
        }
        return null;
    }

    @Override
    public List<Client> recupereTout() {
        List<Client> copies = new ArrayList<>();
        for (Client client : clients.values()) {
            copies.add(copier(client));
        }
        return copies;
    }

    private static Client copier(Client client) {
        return new Client(client.getLogin(), client.getMail(), client.getNom(), client.getPrenom(),
                client.getArgent(), client.getPassword(), client.getDateNaissance());
    }
}
