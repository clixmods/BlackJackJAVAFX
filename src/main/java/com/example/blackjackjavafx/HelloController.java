package com.example.blackjackjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label clixText;
    @FXML
    protected void onStartGameButtonClick() {
        clixText.setText("Salut Clix");
        SceneHandler.SetScene(SceneHandler.sceneGame);
    }
}