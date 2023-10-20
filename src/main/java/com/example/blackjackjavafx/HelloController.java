package com.example.blackjackjavafx;

import com.example.blackjackjavafx.gameState.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label clixText;
    @FXML
    protected void onStartGameButtonClick() {
        clixText.setText("Salut Clix");
        BlackJackApplication.gameStateInitiater.notify(GameState.SelectionMise);
        SceneHandler.SetScene(SceneHandler.sceneGame);

    }
}