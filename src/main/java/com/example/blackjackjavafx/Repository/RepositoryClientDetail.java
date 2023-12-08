package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Metier.ClientDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryClientDetail extends Repository<ClientDetail> {
    @Override
    protected String getNomTable() {
        return "temp_client_detail";
    }

    @Override
    protected String[] nomAttributsDansTable() {
        return new String[]{
                "idClient",
                "adresse",
                "codepostal",
                "ville",
                "pays",
                "dateNaissance",
                "dateInscription",
                "telephone"};
    }

    @Override
    protected Object[] convertirValeursAttributsEnTableauObjets(ClientDetail object) {
        return new Object[]{
                object.getIdClient(),
                object.getAdresse(),
                object.getCodePostal(),
                object.getVille(),
                object.getVille(),
                object.getDateNaissance(),
                object.getDateInscription(),
                object.getTelephone()
        };
    }

    @Override
    protected Object getClePrimaireValeur(ClientDetail object) {
        return object.getIdClient();
    }

    @Override
    protected boolean EstClePrimaireAutoGenerer() {
        return true;
    }
    @Override
    protected ClientDetail creerObjetDepuisResultat(ResultSet resultSet) throws SQLException {
        return new ClientDetail(
                resultSet.getInt("idClient"),
                resultSet.getString("adresse"),
                resultSet.getInt("codepostal"),
                resultSet.getString("ville"),
                //resultSet.getString("pays"),
                resultSet.getDate("dateNaissance"),
                resultSet.getDate("dateInscription"),
                resultSet.getString("telephone"));

    }
}
