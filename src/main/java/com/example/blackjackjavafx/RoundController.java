package com.example.blackjackjavafx;

import com.example.blackjackjavafx.card.Card;
import com.example.blackjackjavafx.gameState.GameState;
import com.example.blackjackjavafx.gameState.IGameStateListener;
import com.example.blackjackjavafx.notifier.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoundController implements Initializable  {

    //region FXML variables
    @FXML
    public List<Card> playerHand = new ArrayList<>();
    @FXML
    public List<Card> dealerHand = new ArrayList<>();
    @FXML
    public Label handPlayerText;
    public Label handDealerText;
    public Button buttonStand;
    public Button buttonHit;

    public Label messageRoundText;
    public Button buttonRestartRound;
    public Label deckSizeText;
    public HBox cardBoxPlayer;
    public HBox cardBoxDealer;
    public Label miseRoundText;
    public SubScene subSceneMise;
    //endregion
    private DeckHandler deck;
    private Integer miseInGame;
    // region Getter Setter
    public int GetPlayerHandValue() { return GetHandValue(playerHand); }
    public int GetDealerHandValue() { return GetHandValue(dealerHand); }
    //endregion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Register the class on the gameStateInitiater
        BlackJackApplication.gameStateInitiater.addListener(new Listener<GameState>() {
            @Override
            public void onEvent(GameState event) {
                switch (event) {
                    case MainMenu -> {

                    }
                    case PreRound -> {
                        PreRound();
                    }
                    case StartRound -> {
                        StartRound();
                    }
                    case PlayerTurn -> {
                        PlayerTurn();
                    }
                    case DealerTurn -> {
                        DealerTurn();
                    }
                    case ComparateCards -> {
                        RoundResult();
                    }
                    case EndOfRound -> {
                        buttonRestartRound.setVisible(true);
                    }
                }
            }
        });
        BlackJackApplication.miseNotifier.addListener(new Listener<Integer>() {
            @Override
            public void onEvent(Integer event) {
                miseInGame = event;
                miseRoundText.setText("Votre mise :"+miseInGame);
            }
        });


    }

    private void PreRound()
    {
        // Disable player button
        buttonStand.setVisible(false);
        buttonHit.setVisible(false);
        messageRoundText.setText("Préparation");
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        deck = new DeckHandler();
        buttonRestartRound.setVisible(false);

    }
    private void StartRound() {

        messageRoundText.setText("Distribution des cartes");
        for (int i = 0; i < 2; i++)
        {
            playerHand.add(deck.Distribute());
            dealerHand.add(deck.Distribute());
        }
        deckSizeText.setText("Taille du deck : "+deck.get_deck().size());

        handPlayerText.setText("Votre main : " + GetPlayerHandValue());
        handDealerText.setText("Main du croupier : " + GetDealerHandValue());

        BlackJackApplication.gameStateInitiater.notify(GameState.PlayerTurn);
    }

    private void PlayerTurn() {
        messageRoundText.setText("Faites un choix !");
        buttonHit.setVisible(true);
        buttonStand.setVisible(true);

        UpdateCardBoxPlayer(cardBoxDealer,dealerHand);
        UpdateCardBoxPlayer(cardBoxPlayer,playerHand);
    }

    private void EndPlayerTurn() {
        messageRoundText.setText("Fin de tour du joueur");
        // Disable player button
        buttonStand.setVisible(false);
        buttonHit.setVisible(false);
        BlackJackApplication.gameStateInitiater.notify(GameState.DealerTurn);
    }
    private void DealerTurn() {
        messageRoundText.setText("Tour du croupier");
        // Tour du croupier
        while (GetDealerHandValue() < 17) {
            dealerHand.add(deck.Distribute());
            UpdateCardBoxPlayer(cardBoxDealer,dealerHand);
            handDealerText.setText("Main du croupier : " + GetDealerHandValue());
        }
        BlackJackApplication.gameStateInitiater.notify(GameState.ComparateCards);
    }
    private void RoundResult()
    {
        int playerScore = GetPlayerHandValue();
        int dealerScore = GetDealerHandValue();
        // Détermine le résultat
        if (playerScore > 21)
        {
            messageRoundText.setText("Vous avez perdu !");
        }
        else if (dealerScore > 21 || playerScore > dealerScore)
        {
            messageRoundText.setText("Vous avez gagné !");
        }
        else if (playerScore == dealerScore)
        {
            messageRoundText.setText("Égalité !");
        } else {
            messageRoundText.setText("Le croupier a gagné !");
        }
        BlackJackApplication.gameStateInitiater.notify(GameState.EndOfRound);
    }


    // region Button Event
    public void onRestartButtonClick(ActionEvent actionEvent)
    {
        BlackJackApplication.gameStateInitiater.notify(GameState.PreRound);
    }

    @FXML
    public void onStandButtonClick(ActionEvent actionEvent)
    {
        EndPlayerTurn();
    }
    @FXML
    public void onHitButtonClick(ActionEvent actionEvent)
    {
        playerHand.add(deck.Distribute());
        int playerHandValue = GetPlayerHandValue();
        handPlayerText.setText("Votre main : " + playerHandValue);
        UpdateCardBoxPlayer(cardBoxPlayer,playerHand);
        if(playerHandValue >= 21)
        {
            EndPlayerTurn();
        }

    }
    //endregion



    private void UpdateCardBoxPlayer(HBox hBox, List<Card> hand) {
        hBox.getChildren().clear();
        for (int i = 0; i < hand.size(); i++)
        {
            InputStream inputStream = getClass().getResourceAsStream(hand.get(i).get_imageURL());
            if(inputStream != null)
            {
                Image cardImage = new Image(inputStream);
                ImageView cardImageView = new ImageView(cardImage);
                cardImageView.setFitWidth(100); // Ajustez la largeur de la carte
                cardImageView.setPreserveRatio(true);
                cardImageView.setSmooth(true);
                hBox.getChildren().add(cardImageView);
            }

        }
        hBox.setVisible(true);
    }
    private int GetHandValue(List<Card> hand)
    {
        int score = 0;
        int numAces = 0;

        for (int i = 0; i < hand.size(); i++)
        {
            Card card = hand.get(i);
            if (card.get_value() == 1) {
                score += 11;
                numAces++;
            } else if (card.get_value() > 10) {
                score += 10;
            } else {
                score += card.get_value();
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }



}
