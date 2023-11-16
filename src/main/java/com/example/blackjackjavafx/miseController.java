package com.example.blackjackjavafx;

import com.example.blackjackjavafx.Application.ControleurJeu;
import com.example.blackjackjavafx.gameState.GameState;
import com.example.blackjackjavafx.jeton.Jeton;
import com.example.blackjackjavafx.jeton.JetonHandler;
import com.example.blackjackjavafx.notifier.Listener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;



public class miseController implements Initializable {
    public Label argentJoueurText;


    private class ButtonJetonData
    {
        public Button button;
        public Jeton jeton;

        public ButtonJetonData(Button button , Jeton jeton)
        {
            this.button = button;
            this.jeton = jeton;
        }
    }
    public Button buttonValidMise;
    public HBox hBoxJetons;
    public Label miseJoueurText;
    private int _mise;
    public static boolean doubleMise;
    public List<ButtonJetonData> buttonsJeton = new ArrayList<>() {
    };
   
    
    private JetonHandler jetonHandler;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jetonHandler = new JetonHandler();
        BlackJackApplication.Money.addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                argentJoueurText.setText("Argent du joueur : "+newValue+" €");
                jetonHandler.UpdateJetonFromMoney((Integer) newValue);
                UIUpdateButtonsJeton();
            }
        });
        // Le miseController va écouter les changements de phase de jeu
        BlackJackApplication.gameStateInitiater.addListener(new Listener<GameState>() {
            @Override
            public void onEvent(GameState event) {
                // Si on entre en phase SelectionMise, on prepare les jetons
                if (Objects.requireNonNull(event) == GameState.SelectionMise) {
                    miseJoueurText.setText("Selectionner votre mise");
                    doubleMise = false;
                    _mise = 0;
                    argentJoueurText.setText("Argent du joueur : "+BlackJackApplication.Money.getValue()+" €");
                    jetonHandler.UpdateJetonFromMoney(BlackJackApplication.Money.getValue());
                    UIUpdateButtonsJeton();
                }
                // Si le joueur a gagné
                else if (Objects.requireNonNull(event) == GameState.PlayerWin) {
                    int miseBonus = _mise;
                    _mise *= 2;
                    if(doubleMise)
                    {
                        _mise += miseBonus;
                    }
                    BlackJackApplication.Money.setValue(BlackJackApplication.Money.getValue() + _mise);
                    _mise = 0;
                }
                else if (Objects.requireNonNull(event) == GameState.Equality) {
                    BlackJackApplication.Money.setValue(BlackJackApplication.Money.getValue() + _mise);
                    _mise = 0;
                }
                else if (Objects.requireNonNull(event) == GameState.PlayerLose) {
                    if(doubleMise)
                    {
                        BlackJackApplication.Money.setValue(BlackJackApplication.Money.getValue() - _mise);
                    }
                    _mise = 0;
                }
            }
        });


        UIGenerateJetonsButtons();
    }

    private void UIGenerateJetonsButtons() {
        hBoxJetons.getChildren().clear();
        for (int i = 0; i < jetonHandler.baseJetons.size(); i++)
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
                        BlackJackApplication.Money.setValue(BlackJackApplication.Money.getValue() - jetonHandler.baseJetons.get(finalI).get_value());  ;

                        miseJoueurText.setText("Votre mise "+_mise);

                        UIUpdateButtonsJeton();
                    }); ;
                    hBoxJetons.getChildren().add(buttonJeton);
                    buttonsJeton.add(new ButtonJetonData(buttonJeton, jetonHandler.baseJetons.get(i)));
                }
        }
    }

    public void onAjoutezArgentButtonClick(ActionEvent actionEvent) {
        BlackJackApplication.Money.setValue(BlackJackApplication.Money.get() + 1000);
    }

    public void onValidButtonClick(ActionEvent actionEvent) {
        if(_mise > 0)
        {
            BlackJackApplication.miseNotifier.notify(_mise);
            BlackJackApplication.gameStateInitiater.notify(GameState.StartRound);
        }
        else
        {
            miseJoueurText.setText("Vous ne pouvez jouer si vous misez pas.");
        }

        //_mise = 0;

    }
    /**
     * Cette méthode permet de gérer de check la disponibilité des jetons. Elle gère ainsi l'affichage de l'UI.
     * N'hésitez pas à l'appeller lorsque le @JetonHandler effectue un changement (notifier ?)
     */
    private void UIUpdateButtonsJeton() {
        for (int i = 0; i < buttonsJeton.size(); i++)
        {
            buttonsJeton.get(i).button.setVisible(jetonHandler.jetons.contains(buttonsJeton.get(i).jeton));
        }
        hBoxJetons.setVisible(true);
    }
}
