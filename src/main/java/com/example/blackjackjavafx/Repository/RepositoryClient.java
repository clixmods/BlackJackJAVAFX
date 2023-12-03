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
                "login",
                "mail",
                "nom",
                "prenom",
                "dateNaissance",
                "dateInscription",
                "telephone",
                "argent",
                "adresse",
                "codepostal",
                "ville",
                "pays",
                "password"};
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
        return object.getLogin();
    }

    @Override
    protected boolean EstClePrimaireAutoGenerer() {
        return true;
    }
    @Override
    protected Client creerObjetDepuisResultat(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("login"),
                resultSet.getString("mail"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getDate("dateNaissance"),
                resultSet.getDate("dateInscription"),
                resultSet.getString("telephone"),
                resultSet.getInt("argent"),
                resultSet.getString("adresse"),
                resultSet.getInt("codepostal"),
                resultSet.getString("ville"),
                resultSet.getString("pays"),
                resultSet.getString("password"));

    }
}
