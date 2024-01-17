package com.example.blackjackjavafx.Metier;

import com.example.blackjackjavafx.Application.controller.ControleurJeu;

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
        //pioche.melanger();
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
            client.ajouterArgent(2*miseActuelle + miseActuelle/2);
            controleur.mettreAJourAffichageValeurMainJoueur(joueur.getValeurMain());
            controleur.afficherBlackJack(miseActuelle*2 + miseActuelle/2);
        }
        else {
            controleur.mettreAJourAffichageValeurMainJoueur(joueur.getValeurMain());
        }
    }

    public boolean joueurPiocheEtGagne(){
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
        if (joueurPiocheEtGagne()){
            controleur.finTourJoueur();
        }
    }

    public void tourCroupier(){
        croupier.calculerValeurMain();
        while (croupier.getValeurMain()<17){
            Carte carte = croupier.piocher(pioche);
            controleur.mettreAJourAffichageCartesCroupier(carte);
            croupier.calculerValeurMain();
        }
        if (croupier.getValeurMain()>21){
            client.ajouterArgent(miseActuelle*2);
            controleur.afficherVictoire(miseActuelle*2);
        }
        else {
            comparerResultats();
        }
    }

    public void comparerResultats(){
        if (joueur.getValeurMain()>croupier.getValeurMain()){
            client.ajouterArgent(miseActuelle*2);
            controleur.afficherVictoire(miseActuelle*2);
        }
        else if (joueur.getValeurMain() == croupier.getValeurMain()){
            client.ajouterArgent(miseActuelle);
            controleur.afficherEgalite();
        }
        else{
            controleur.afficherDefaite();
        }
    }


    public void defaite(){
        controleur.afficherDefaite();
    }
}
