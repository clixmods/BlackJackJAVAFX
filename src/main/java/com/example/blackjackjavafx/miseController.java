package com.example.blackjackjavafx;

import com.example.blackjackjavafx.card.Card;
import com.example.blackjackjavafx.gameState.GameState;
import com.example.blackjackjavafx.jeton.JetonHandler;
import com.example.blackjackjavafx.notifier.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class miseController implements Initializable {
    public Button buttonValidMise;
    public Button buttonMiseTest;
    public HBox hBoxJetons;
    public Label miseJoueurText;
    private int _mise;

    private int money = 1000;
    private JetonHandler jetonHandler;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jetonHandler = new JetonHandler();
        BlackJackApplication.gameStateInitiater.addListener(new Listener<GameState>() {
            @Override
            public void onEvent(GameState event) {
                if (Objects.requireNonNull(event) == GameState.PreRound) {
                    jetonHandler.UpdateJetonFromMoney(money);
                    UpdateJetons();
                }
            }
        });
    }

    public void onValidButtonClick(ActionEvent actionEvent) {
        BlackJackApplication.miseNotifier.notify(_mise);
        BlackJackApplication.gameStateInitiater.notify(GameState.StartRound);
        _mise = 0;

    }

    public void onJetonTestButtonClick(ActionEvent actionEvent) {
        _mise ++;
        money -=  1;
        jetonHandler.UpdateJetonFromMoney(money);
        UpdateJetons();
    }


    private void UpdateJetons() {
        hBoxJetons.getChildren().clear();
        System.out.print(jetonHandler.baseJetons.size()+"\n");

        System.out.print(jetonHandler.jetons.size());

        for (int i = 0; i < jetonHandler.baseJetons.size(); i++)
        {

            if(jetonHandler.jetons.contains(jetonHandler.baseJetons.get(i)))
            {

                InputStream inputStream = getClass().getResourceAsStream(jetonHandler.baseJetons.get(i).get_imageURL());
                //if(inputStream != null)
                {
                    Image cardImage = new Image(inputStream);
                    ImageView cardImageView = new ImageView(cardImage);
                    cardImageView.setFitWidth(100); // Ajustez la largeur de la carte
                    cardImageView.setPreserveRatio(true);
                    cardImageView.setSmooth(true);
                    Button buttonJeton = new Button();
                    buttonJeton.maxWidth(100);
                    buttonJeton.maxHeight(100);
                    buttonJeton.setGraphic(cardImageView);
                    int finalI = i;
                    buttonJeton.setOnAction(actionEvent -> {
                        _mise += jetonHandler.baseJetons.get(finalI).get_value() ;
                        money -=  jetonHandler.baseJetons.get(finalI).get_value();

                        miseJoueurText.setText("Votre mise "+_mise);
                        jetonHandler.UpdateJetonFromMoney(money);
                        UpdateJetons();
                    }); ;
                    hBoxJetons.getChildren().add(buttonJeton);
                }
            }


        }
        hBoxJetons.setVisible(true);
    }
}
