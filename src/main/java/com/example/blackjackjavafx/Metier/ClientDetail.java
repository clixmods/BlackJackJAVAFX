package com.example.blackjackjavafx.Metier;

import java.util.Date;

public class ClientDetail
{
    private int idClient;

    public int getIdClient() {
        return idClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public String getTelephone() {
        return telephone;
    }

    private String adresse;

    private int codePostal;

    private String ville;

    private Date dateNaissance;

    private Date dateInscription;

    private String telephone;

    public ClientDetail(int idClient, String adresse, int codePostal, String ville, Date dateNaissance, Date dateInscription, String telephone)
    {
        this.idClient = idClient;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.telephone = telephone;
    }
}
