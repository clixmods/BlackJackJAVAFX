package com.example.blackjackjavafx.Repository.Sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Charge les paramètres sensibles (base de données, pepper) depuis le fichier config.properties.
 * Ce fichier n'est pas versionné : voir config.example.properties.
 */
public class Configuration
{
    private static final String FICHIER = "/config.properties";

    private static Properties properties;

    private Configuration() {}

    public static boolean estPresente()
    {
        return Configuration.class.getResource(FICHIER) != null;
    }

    public static String get(String cle)
    {
        if (properties == null)
        {
            properties = charger();
        }
        String valeur = properties.getProperty(cle);
        if (valeur == null)
        {
            throw new IllegalStateException("Clé \"" + cle + "\" absente de " + FICHIER);
        }
        return valeur;
    }

    private static Properties charger()
    {
        try (InputStream inputStream = Configuration.class.getResourceAsStream(FICHIER))
        {
            if (inputStream == null)
            {
                throw new IllegalStateException(FICHIER + " introuvable : copiez config.example.properties en config.properties");
            }
            Properties chargees = new Properties();
            chargees.load(inputStream);
            return chargees;
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Impossible de lire " + FICHIER, e);
        }
    }
}
