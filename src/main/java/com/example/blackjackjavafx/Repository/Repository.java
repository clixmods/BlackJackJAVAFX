package com.example.blackjackjavafx.Repository;

import com.example.blackjackjavafx.Repository.Sql.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> implements I_Repository<T> {

    protected abstract String getNomTable() ;

    protected String getNomClePrimaire(){
        return nomAttributsDansTable()[0];
    }

    /**
     * Il faut que l'ordre des attributs soient le même que celui de la base de donnée.
     * @return Retourne le nom de chaque attributs présent dans la table,
     * La première doit être la clé primaire
     */
    protected abstract String[] nomAttributsDansTable();


    /**
     * Il est OBLIGATOIRE de l'implanter pour s'assurer du bon fonctionnement du repository
     * Cette méthode est principalement utilisé pour les requêtes SQL.
     * @param object Objet pour lequel on va récupérer chaque attribut
     * @return Retourne un tableau d'Object, ce tableau est composé des différents attributs dans T qui sont castés en tant qu'Object
     */
    protected abstract Object[] convertirValeursAttributsEnTableauObjets(T object);

    protected abstract Object getClePrimaireValeur(T object);

    protected abstract boolean EstClePrimaireAutoGenerer();

    /**
     * @param resultSet resultSet provenant généralement d'une requete SQL
     * @return Renvoi l'objet convertit depuis un resultSet
     * @throws SQLException
     */
    protected abstract T creerObjetDepuisResultat(ResultSet resultSet) throws SQLException;

    /**
     * @param element Insert un élément dans la base de donnée
     */
    public void inserer(T element) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String request = "INSERT INTO "+ getNomTable()+" (";
        genereValuesEtUpdate(element, connection, request);
    }

    public void mettreAJour(T element)
    {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();

        String request = "UPDATE "+ getNomTable()+" SET ";

        String[] attributs = nomAttributsDansTable();

        int startIndex = getStartIndex();

        for(int i = startIndex; i < attributs.length; i++)
        {
            if (i < attributs.length - 1) {
                request += attributs[i] + " = ?, ";
            } else {
                request += attributs[i] + " = ?";
            }
        }

        request += " WHERE "+ getNomClePrimaire()+" = ?";

        System.out.println(request);

        Object[] values = convertirValeursAttributsEnTableauObjets(element);

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            // L'index parameter de SQL commence à 1...
            int indexParameterSQL = 1;

            for (int i = startIndex; i < attributs.length; i++)
            {
                statement.setObject(indexParameterSQL , values[i]);
                indexParameterSQL++;
            }
            statement.setObject(attributs.length, getClePrimaireValeur(element));

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int valeurClePrimaire) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String request = "DELETE FROM "+ getNomTable();

        request += " WHERE "+ getNomClePrimaire()+" = ?";

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            statement.setInt(1, valeurClePrimaire);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T recupere(int valeurClePrimaire) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String request = "SELECT * FROM "+ getNomTable();

        request += " WHERE "+ getNomClePrimaire()+" = ?";

        List<T> resultList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            statement.setInt(1, valeurClePrimaire);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T item = creerObjetDepuisResultat(resultSet);
                resultList.add(item);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList.get(0);
    }

    public T recupereBy(String nomColonne, String valeur) {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String request = "SELECT * FROM "+ getNomTable();

        request += " WHERE "+nomColonne+" = ?";

        List<T> resultList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            statement.setString(1, valeur);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T item = creerObjetDepuisResultat(resultSet);
                resultList.add(item);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if(resultList.size() == 0)
        {
            return null;
        }
        return resultList.get(0);
    }

    public List<T> recupereTout() {
        SQLUtils utils = SQLUtils.getInstance();
        Connection connection = utils.getConnection();
        String request = "SELECT * FROM "+ getNomTable();

        List<T> resultList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T item = creerObjetDepuisResultat(resultSet);
                resultList.add(item);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private void genereValuesEtUpdate(T element, Connection connection, String request) {
        String[] attributs = nomAttributsDansTable();

        int startIndex = getStartIndex();

        for(int i = startIndex ; i < attributs.length; i++)
        {
            if (i < attributs.length - 1) {
                request += attributs[i] + ", ";
            } else {
                request += attributs[i]+") ";
            }
        }
        request += "VALUES (";
        for(int i = startIndex; i < attributs.length; i++)
        {
            if (i < attributs.length - 1) {
                request += "?, ";
            } else {
                request += "?) ";
            }
        }

        Object[] values = convertirValeursAttributsEnTableauObjets(element);

        try(PreparedStatement statement = connection.prepareStatement(request))
        {
            // L'index parameter de SQL commence à 1...
            int indexParameter = 1;

            for (int i = startIndex; i < attributs.length; i++)
            {
                statement.setObject(indexParameter , values[i]);
                indexParameter ++;
            }

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getStartIndex() {
        int startIndex = 0;
        if(EstClePrimaireAutoGenerer())
        {
            startIndex = 1;
        }
        return startIndex;
    }

}
