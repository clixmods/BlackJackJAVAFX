package com.example.blackjackjavafx.Metier;

import java.time.LocalDate;

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
    //region VARIABLES

    private String login;

    private String mail;

    private LocalDate dateNaissance;

    private String nom;

    private String prenom;

    private int argent;

    private String password;
    //endregion

    //region PROPERTIES

    public String getLogin(){
        return login;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public int getArgent(){
        return argent;
    }

    public String getPassword(){
        return password;
    }
    //endregion

    public Client(String login, String mail, String nom, String prenom, int argent,String password,LocalDate dateNaissance){
        this.login = login;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.argent = argent;
        this.password = password;
        this.dateNaissance = dateNaissance;
    }

    public void ajouterArgent(int somme){
        argent += somme;
    }

    public void retirerArgent(int somme){
        argent -= somme;
    }



}
