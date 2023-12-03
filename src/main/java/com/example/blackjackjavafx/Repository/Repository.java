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
     * @return Retourne le nom de chaque attributs présent dans la table,
     * La première doit être la clé primaire
     */
    protected abstract String[] nomAttributsDansTable();

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
            for (int i = startIndex; i < attributs.length; i++)
            {
                if(startIndex == 1)
                {
                    statement.setObject(i , values[i-1]);
                }
                else
                {
                    statement.setObject(i , values[i]);
                }
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
            for (int i = startIndex; i < attributs.length; i++)
            {
                if(startIndex == 1)
                {
                    statement.setObject(i , values[i-1]);
                }
                else
                {
                    statement.setObject(i , values[i]);
                }

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
