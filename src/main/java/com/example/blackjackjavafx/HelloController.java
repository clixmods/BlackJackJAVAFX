package com.example.blackjackjavafx;

import com.example.blackjackjavafx.gameState.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label clixText;
    @FXML
    protected void onStartGameButtonClick() throws IOException {
        clixText.setText("Salut Clix");
        BlackJackApplication.gameStateInitiater.notify(GameState.SelectionMise);
        SceneHandler.SetScene(SceneHandler.sceneGame);

    }
}