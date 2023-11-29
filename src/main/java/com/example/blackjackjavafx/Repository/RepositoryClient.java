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
        return new String[]{ "login", "nom","argent"};
    }

    @Override
    protected Object[] convertirValeursAttributsEnTableauObjets(Client object) {
        return new Object[]{
                object.getNom(),
                object.getArgent()
        };
    }

    @Override
    protected Object getClePrimaireValeur(Client object) {
        return object.getNom();
    }

    @Override
    protected boolean EstClePrimaireAutoGenerer() {
        return true;
    }
    @Override
    protected Client creerObjetDepuisResultat(ResultSet resultSet) throws SQLException {
        return new Client(resultSet.getString("nom"), resultSet.getInt("argent"));
    }
}
