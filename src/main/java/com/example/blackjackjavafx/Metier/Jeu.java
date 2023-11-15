package com.example.blackjackjavafx.Metier;

import com.example.blackjackjavafx.Application.ControleurJeu;

public class Jeu {

    private Client client;

    private Pioche pioche;

    private Joueur joueur;

    private Participant croupier;

    private ControleurJeu controleur;

    private int miseActuelle;

    public Jeu(Client client, ControleurJeu controleur, int miseActuelle){
        this.client = client;
        this.controleur = controleur;
        this.miseActuelle = miseActuelle;
        this.joueur = new Joueur(client.getNom());
        this.croupier = new Participant();
        this.pioche = new Pioche();
        pioche.melanger();
    }

    public void distribuerCartes(){
        Carte carte = joueur.piocher(pioche);
        controleur.mettreAJourAffichageCartesJoueur(carte);
    }
}
