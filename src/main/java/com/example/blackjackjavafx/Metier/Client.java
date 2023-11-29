package com.example.blackjackjavafx.Metier;

public class Client {

/*    private static Client _current;
    public static Client GetCurrent()
    {
        return _current;
    }
    public static boolean LogoutClient()
    {
        _current = null;
        return true;
    }

    public static boolean LoginClient(Client newClient)
    {
        if(_current != null)
        {
            LogoutClient();
        }
        _current = newClient;
        return true;
    }*/



    private String nom;

    private int argent;

    public Client(String nom, int argent){
        this.nom = nom;
        this.argent = argent;
    }

    public int getArgent(){
        return argent;
    }

    public String getNom(){
        return nom;
    }

    public void ajouterArgent(int somme){
        argent += somme;
    }

    public void retirerArgent(int somme){
        argent -= somme;
    }
}
