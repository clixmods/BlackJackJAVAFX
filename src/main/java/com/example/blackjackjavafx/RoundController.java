package com.example.blackjackjavafx;

import com.example.blackjackjavafx.card.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.ResourceBundle;

public class RoundController implements Initializable {

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
    //endregion
    private DeckHandler deck;
    // region Getter Setter
    public int GetPlayerHandValue() { return GetHandValue(playerHand); }
    public int GetDealerHandValue() { return GetHandValue(dealerHand); }
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonRestartRound.setVisible(false);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        deck = new DeckHandler();
        for (int i = 0; i < 2; i++)
        {
            playerHand.add(deck.Distribute());
            dealerHand.add(deck.Distribute());
        }
        deckSizeText.setText("Taille du deck : "+deck.get_deck().size());
        messageRoundText.setText("Faites un choix !");
        handPlayerText.setText("Votre main : " + GetPlayerHandValue());
        handDealerText.setText("Main du croupier : " + GetDealerHandValue());

        buttonHit.setVisible(true);
        buttonStand.setVisible(true);

        UpdateCardBoxPlayer(cardBoxDealer,dealerHand);
        UpdateCardBoxPlayer(cardBoxPlayer,playerHand);

    }
    // region Button Event
    public void onRestartButtonClick(ActionEvent actionEvent)
    {
        initialize(null,null);
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
    private void EndPlayerTurn() {
        // Disable player button
        buttonStand.setVisible(false);
        buttonHit.setVisible(false);

        DealerTurn();
        RoundResult();
        buttonRestartRound.setVisible(true);

    }

    private void RoundResult()
    {
        int playerScore = GetPlayerHandValue();
        int dealerScore = GetDealerHandValue();
        // Détermine le résultat
        if (playerScore > 21) {

            messageRoundText.setText("Vous avez perdu !");
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            messageRoundText.setText("Vous avez gagné !");
        } else if (playerScore == dealerScore) {
            messageRoundText.setText("Égalité !");
        } else {
            messageRoundText.setText("Le croupier a gagné !");
        }
    }

    private void DealerTurn() {
        BlackJackApplication.state = GameState.DealerTurn;
        // Tour du croupier
        while (GetDealerHandValue() < 17) {
            dealerHand.add(deck.Distribute());
            UpdateCardBoxPlayer(cardBoxDealer,dealerHand);
            handDealerText.setText("Main du croupier : " + GetDealerHandValue());
        }
    }

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
