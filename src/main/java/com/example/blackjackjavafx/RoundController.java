package com.example.blackjackjavafx;

import com.example.blackjackjavafx.card.Card;
import com.example.blackjackjavafx.gameState.GameState;
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
    @FXML
    public Label handDealerText;
    @FXML
    public Button buttonStand;
    @FXML
    public Button buttonHit;
    public Button buttonDouble;
    @FXML
    public Label messageRoundText;
    @FXML
    public Button buttonRestartRound;
    @FXML
    public Label deckSizeText;
    @FXML
    public HBox cardBoxPlayer;
    @FXML
    public HBox cardBoxDealer;
    @FXML
    public Label miseRoundText;
    @FXML
    public SubScene subSceneMise;

    @FXML
    public HBox buttonBoxPlayer;
    
    //endregion
    private DeckHandler deck;
    private Integer miseInGame = 0;
    private boolean playerUseDouble;
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
                    case SelectionMise -> {
                        SelectionMise();
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
                miseRoundText.setText("Votre mise : "+miseInGame+ " €");
            }
        });


    }

    private void SelectionMise()
    {
        // Disable player button
        SetVisiblePlayerButtons(false);
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
            PlayerHitCard();
        }
        DealerHitCard();
        deckSizeText.setText("Taille du deck : "+deck.get_deck().size());
        BlackJackApplication.gameStateInitiater.notify(GameState.PlayerTurn);

    }
    private void PlayerTurn() {
        messageRoundText.setText("Faites un choix !");
        SetVisiblePlayerButtons(true);
    }
    private void EndPlayerTurn() {
        messageRoundText.setText("Fin de tour du joueur");
        SetVisiblePlayerButtons(false);
        BlackJackApplication.gameStateInitiater.notify(GameState.DealerTurn);
    }

    private void SetVisiblePlayerButtons(boolean value) {
        // Disable player button
        buttonBoxPlayer.setVisible(value);
        buttonStand.setVisible(value);
        buttonHit.setVisible(value);
        // On check si il peut double, sinon on cache de force le boutton
//        if(BlackJackApplication.Money.getValue() < miseInGame )
//        {
//            buttonDouble.setVisible(false);
//        }
//        else
//        {
//            buttonDouble.setVisible(value);
//        }

    }

    private void DealerTurn() {
        messageRoundText.setText("Tour du croupier");
        // Tour du croupier
        while (GetDealerHandValue() < 17) {
            DealerHitCard();
        }
        BlackJackApplication.gameStateInitiater.notify(GameState.ComparateCards);
    }
    private void RoundResult()
    {
        BlackJackApplication.gameStateInitiater.notify(GameState.EndOfRound);
        int playerScore = GetPlayerHandValue();
        int dealerScore = GetDealerHandValue();
        // Détermine le résultat
        if (playerScore > 21)
        {
            messageRoundText.setText("Vous avez perdu ! \n Vous avez perdu "+miseInGame+ " €");
            CallGameStateNotifierForPlayerLose();
        }
        else if (dealerScore > 21 || playerScore > dealerScore)
        {
            messageRoundText.setText("Vous avez gagné ! \n Vous remportez "+miseInGame+ " €");
            BlackJackApplication.gameStateInitiater.notify(GameState.PlayerWin);
        }
        else if (playerScore == dealerScore)
        {
            messageRoundText.setText("Égalité !");
            BlackJackApplication.gameStateInitiater.notify(GameState.Equality);
        }
        else
        {
            messageRoundText.setText("Le croupier a gagné ! \n Vous avez perdu "+miseInGame+ " €");
            CallGameStateNotifierForPlayerLose();
        }

    }

    private void CallGameStateNotifierForPlayerLose() {
        BlackJackApplication.gameStateInitiater.notify(GameState.PlayerLose);
    }

    // region Button Event
    public void onRestartButtonClick(ActionEvent actionEvent)
    {
        BlackJackApplication.gameStateInitiater.notify(GameState.SelectionMise);
    }

    @FXML
    public void onStandButtonClick(ActionEvent actionEvent)
    {
        EndPlayerTurn();
    }
    @FXML
    public void onHitButtonClick(ActionEvent actionEvent)
    {
        int playerHandValue = PlayerHitCard();
        if(playerHandValue >= 21)
        {
            EndPlayerTurn();
        }

    }
    public void onDoubleButtonClick(ActionEvent actionEvent)
    {
        miseController.doubleMise = true;
        miseInGame *= 2;
        PlayerHitCard();
        EndPlayerTurn();
    }


    //endregion

    /**
     * Instruction when a player hit a card
     * @return
     */
    private int PlayerHitCard() {
        playerHand.add(deck.Distribute());
        int playerHandValue = GetPlayerHandValue();
        handPlayerText.setText("Votre main : " + playerHandValue);
        UIUpdateCardBox(cardBoxPlayer,playerHand);
        return playerHandValue;
    }

    private int DealerHitCard() {
        dealerHand.add(deck.Distribute());
        int dealerHandValue = GetDealerHandValue();
        handDealerText.setText("Main du croupier : " + dealerHandValue);
        UIUpdateCardBox(cardBoxDealer,dealerHand);
        return dealerHandValue;
    }

    /**
     * Gère l'affichage des cartes du joueur
     * @param hBox
     * @param hand
     */
    private void UIUpdateCardBox(HBox hBox, List<Card> hand) {
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

    /**
       Permet de calculer le nombre de point d'une liste de carte suivant les règles du black jack
     */
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
