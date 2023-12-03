package com.example.blackjackjavafx.Application;

import com.example.blackjackjavafx.Metier.Client;
import com.example.blackjackjavafx.Vue.SceneHandler;
import javafx.event.ActionEvent;

public class ControleurAccueil {

    private SceneHandler sceneHandler;
    public void initialiserAccueil(SceneHandler sceneHandler){
        this.sceneHandler = sceneHandler;
    }

    public void onStartGameButtonClick(){
        Client roger = new Client(0,"Roger", resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getDate("dateNaissance"), resultSet.getDate("dateInscription"), resultSet.getString("telephone"), 1000, resultSet.getString("adresse"), resultSet.getInt("codepostal"), resultSet.getString("ville"), resultSet.getString("pays"), resultSet.getString("password"));
        roger.ajouterArgent(500);
        sceneHandler.selectionnerMise(roger);
    }

    public void onInscriptionButtonClick(ActionEvent actionEvent)
    {
        sceneHandler.afficherInscription();
    }
}
