package com.example.blackjackjavafx.Vue;

import com.example.blackjackjavafx.BlackJackApplication;
import javafx.scene.Scene;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;

/**
 * Thème visuel « Fortuna » : polices embarquées et feuille de style commune.
 */
public final class Theme {

    private static final String[] POLICES = {
            "/fonts/Outfit-Regular.ttf",
            "/fonts/Outfit-Medium.ttf",
            "/fonts/Outfit-SemiBold.ttf",
            "/fonts/TiltNeon-Regular.ttf"
    };

    private static boolean policesChargees = false;

    private Theme() {}

    /** Doit être appelé avant le chargement des vues pour que les polices soient connues de la CSS. */
    public static void chargerPolices() {
        if (policesChargees) return;
        for (String police : POLICES) {
            try (InputStream flux = Theme.class.getResourceAsStream(police)) {
                if (flux != null) {
                    Font.loadFont(flux, 12);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        policesChargees = true;
    }

    public static void appliquer(Scene scene) {
        scene.getStylesheets().add(BlackJackApplication.class.getResource("fortuna.css").toExternalForm());
        FeedbackVisuel.installer(scene);
    }
}
