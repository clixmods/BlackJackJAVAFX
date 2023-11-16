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
        Carte carte;
        for (int i = 0; i<2; i++) {
            carte = joueur.piocher(pioche);
            controleur.mettreAJourAffichageCartesJoueur(carte);
        }
        carte =croupier.piocher(pioche);
        controleur.mettreAJourAffichageCartesCroupier(carte);
        joueur.calculerValeurMain();
        if (joueur.getValeurMain() == 21){
            controleur.afficherBlackJack();
        }
        else {
            controleur.mettreAJourAffichageValeurMainJoueur(joueur.getValeurMain());
        }
    }

    public boolean joueurPiocheetGagne(){
        Carte carte = joueur.piocher(pioche);
        controleur.mettreAJourAffichageCartesJoueur(carte);
        joueur.calculerValeurMain();
        controleur.mettreAJourAffichageValeurMainJoueur(joueur.getValeurMain());
        if (joueur.getValeurMain()>21){
            defaite();
            return false;
        }
        return true;
    }

    public boolean peutDoubler(){
        return client.getArgent()>=miseActuelle;
    }
    public void joueurDouble(){
        client.retirerArgent(miseActuelle);
        miseActuelle = miseActuelle * 2;
        if (joueurPiocheetGagne()){
            controleur.finDuTourJoueur();
        }
    }

    public void defaite(){
        controleur.afficherDefaite();
    }
}
