package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Repository.Sql.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryClient extends Repository<Client> {
    @Override
    protected String getNomTable() {
        return "s_clients";
    }

    @Override
    protected String[] nomAttributsDansTable() {
        return new String[]{
                "login",
                "mail",
                "dateNaissance",
                "nom",
                "prenom",
                "argent",
                "password"};
    }

    @Override
    protected Object[] convertirValeursAttributsEnTableauObjets(Client object) {
        return new Object[]{
                object.getLogin(),
                object.getMail(),
                object.getDateNaissance(),
                object.getNom(),
                object.getPrenom(),
                object.getArgent(),
                object.getPassword()
        };
    }

    @Override
    protected Object getClePrimaireValeur(Client object) {
        return object.getLogin();
    }

    @Override
    protected boolean EstClePrimaireAutoGenerer() {
        return false;
    }
    @Override
    protected Client creerObjetDepuisResultat(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getString("login"),
                resultSet.getString("mail"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getInt("argent"),
                resultSet.getString("password"),
                resultSet.getDate("dateNaissance").toLocalDate());

    }

    public void mettreAJourArgent(Client client){
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String requete = "UPDATE s_clients SET argent = ? WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(requete)){
            statement.setInt(1, client.getArgent());
            statement.setString(2, client.getLogin());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
