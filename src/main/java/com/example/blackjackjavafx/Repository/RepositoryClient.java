package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryClient extends Repository<Client> {
    @Override
    protected String getNomTable() {
        return "temp_client";
    }

    @Override
    protected String[] nomAttributsDansTable() {
        return new String[]{
                "id",
                "login",
                "mail",
                "nom",
                "prenom",
                "argent",
                "password"};
    }

    @Override
    protected Object[] convertirValeursAttributsEnTableauObjets(Client object) {
        return new Object[]{
                object.getId(),
                object.getLogin(),
                object.getMail(),
                object.getNom(),
                object.getPrenom(),
                object.getArgent(),
                object.getPassword()
        };
    }

    @Override
    protected Object getClePrimaireValeur(Client object) {
        return object.getId();
    }

    @Override
    protected boolean EstClePrimaireAutoGenerer() {
        return true;
    }
    @Override
    protected Client creerObjetDepuisResultat(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("id"),
                resultSet.getString("login"),
                resultSet.getString("mail"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getInt("argent"),
                resultSet.getString("password"));

    }

}
