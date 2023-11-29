package com.example.blackjackjavafx.Repository.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    private static SQLUtils instance = null;
    private Connection connection;
    private SQLUtils()  {
        // Le garciac a la fin de l'url correspond au nom de votre base de donnée
        String url = "jdbc:mariadb://webinfo.iutmontp.univ-montp2.fr:3316/garciac";
        String driver = "org.mariadb.jdbc.Driver";
        String user = "garciac";
        String pass = "clixmods";
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.connection;
    }
    public static SQLUtils getInstance() {
        if(instance == null) {
            instance = new SQLUtils();
        }
        return instance;
    }
}
