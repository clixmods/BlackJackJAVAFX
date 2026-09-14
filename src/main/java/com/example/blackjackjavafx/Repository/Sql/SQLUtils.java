package com.example.blackjackjavafx.Repository.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    private static SQLUtils instance = null;
    private Connection connection;
    private SQLUtils()  {
        String url = Configuration.get("db.url");
        String driver = "org.mariadb.jdbc.Driver";
        String user = Configuration.get("db.user");
        String pass = Configuration.get("db.password");
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
