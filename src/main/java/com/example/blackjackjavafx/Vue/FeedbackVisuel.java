package com.example.blackjackjavafx.Vue;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.EventTarget;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * Ajoute un retour visuel à tous les éléments cliquables d'une scène :
 * léger agrandissement au survol, enfoncement au clic, curseur main.
 * Les couleurs et halos de survol sont gérés par la feuille de style (fortuna.css).
 * Fonctionne aussi pour les boutons créés dynamiquement (jetons, bouton doubler).
 */
public final class FeedbackVisuel {

    private static final double ECHELLE_SURVOL = 1.05;
    private static final double ECHELLE_CLIC = 0.96;
    private static final Duration DUREE = Duration.millis(140);

    private static final String ECHELLE_BASE = "feedback.echelleBase";
    private static final String TRANSITION = "feedback.transition";

    private FeedbackVisuel() {}

    public static void installer(Scene scene) {
        scene.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
            if (estCliquable(e.getTarget())) survoler((Node) e.getTarget(), true);
        });
        scene.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, e -> {
            if (estCliquable(e.getTarget())) survoler((Node) e.getTarget(), false);
        });
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            Node cliquable = trouverCliquable(e.getTarget());
            if (cliquable != null) animer(cliquable, ECHELLE_CLIC);
        });
        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            Node cliquable = trouverCliquable(e.getTarget());
            if (cliquable != null) animer(cliquable, cliquable.isHover() ? ECHELLE_SURVOL : 1.0);
        });
    }

    private static boolean estCliquable(EventTarget cible) {
        return cible instanceof Button
                || (cible instanceof Node noeud && noeud.getOnMouseClicked() != null);
    }

    private static Node trouverCliquable(EventTarget cible) {
        for (Node noeud = cible instanceof Node n ? n : null; noeud != null; noeud = noeud.getParent()) {
            if (estCliquable(noeud)) return noeud;
        }
        return null;
    }

    private static void survoler(Node noeud, boolean entree) {
        // Un élément grisé (bouton désactivé, bouton home inactif) ne réagit pas
        if (entree && (noeud.isDisabled() || noeud.getOpacity() < 1.0)) return;

        if (!(noeud instanceof Button)) {
            noeud.setCursor(entree ? Cursor.HAND : Cursor.DEFAULT);
        }
        animer(noeud, entree ? ECHELLE_SURVOL : 1.0);
    }

    private static void animer(Node noeud, double facteur) {
        preparer(noeud);
        double base = (double) noeud.getProperties().get(ECHELLE_BASE);
        ScaleTransition transition = (ScaleTransition) noeud.getProperties().get(TRANSITION);
        transition.stop();
        transition.setToX(base * facteur);
        transition.setToY(base * facteur);
        transition.play();
    }

    private static void preparer(Node noeud) {
        if (noeud.getProperties().containsKey(TRANSITION)) return;

        noeud.getProperties().put(ECHELLE_BASE, noeud.getScaleX());
        ScaleTransition transition = new ScaleTransition(DUREE, noeud);
        transition.setInterpolator(Interpolator.SPLINE(0.16, 1, 0.3, 1));
        noeud.getProperties().put(TRANSITION, transition);

        // Quand on change de page pendant un survol, la sortie de la souris n'est pas signalée :
        // on remet l'élément dans son état normal pour qu'il ne reste pas agrandi
        noeud.sceneProperty().addListener((obs, ancienne, nouvelle) -> reinitialiser(noeud));
    }

    private static void reinitialiser(Node noeud) {
        ((ScaleTransition) noeud.getProperties().get(TRANSITION)).stop();
        double base = (double) noeud.getProperties().get(ECHELLE_BASE);
        noeud.setScaleX(base);
        noeud.setScaleY(base);
        noeud.setCursor(Cursor.DEFAULT);
    }
}
