package com.example.blackjackjavafx;

import com.example.blackjackjavafx.card.Card;
import com.example.blackjackjavafx.card.CardAs;
import com.example.blackjackjavafx.card.CardBuche;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckHandler
{
    public List<Card> get_deck() {
        return _deck;
    }

    private List<Card> _deck = new ArrayList<>();

    public DeckHandler()
    {
        // Generate deck
        _deck = new ArrayList<>();
        for(int i = 2 ; i <= 9 ; i++)
        {
            _deck.add(new Card(i+" de piques", i , "/images/cards/"+i+"_of_spades.png"));
        }
        for(int i = 2 ; i <= 9 ; i++)
        {
            _deck.add(new Card(i+" de coeur", i , "/images/cards/"+i+"_of_hearts.png"));
        }
        for(int i = 2 ; i <= 9 ; i++)
        {
            _deck.add(new Card(i+" de Carreau", i , "/images/cards/"+i+"_of_diamonds.png"));
        }
        for(int i = 2 ; i <= 9 ; i++)
        {
            _deck.add(new Card(i+" de Trefle", i , "/images/cards/"+i+"_of_clubs.png"));
        }
        _deck.add(new CardBuche("10 de Trefle", "/images/cards/10_of_clubs.png"));
        _deck.add(new CardBuche("Valet de Trefle" , "/images/cards/jack_of_clubs2.png"));
        _deck.add(new CardBuche("Roi de Trefle", "/images/cards/king_of_clubs2.png"));
        _deck.add(new CardBuche("Reine de Trefle" , "/images/cards/queen_of_clubs2.png"));
        _deck.add(new CardAs("As de Trefle" , "/images/cards/ace_of_clubs.png"));

        _deck.add(new CardBuche("10 de Carreau" , "/images/cards/10_of_diamonds.png"));
        _deck.add(new CardBuche("Valet de Carreau" , "/images/cards/jack_of_diamonds2.png"));
        _deck.add(new CardBuche("Roi de Carreau" , "/images/cards/king_of_diamonds2.png"));
        _deck.add(new CardBuche("Reine de Carreau" , "/images/cards/queen_of_diamonds2.png"));
        _deck.add(new CardAs("As de Carreau" , "/images/cards/ace_of_diamonds.png"));

        _deck.add(new CardBuche("10 de coeur", "/images/cards/10_of_hearts.png"));
        _deck.add(new CardBuche("Valet de coeur" , "/images/cards/jack_of_hearts2.png"));
        _deck.add(new CardBuche("Roi de coeur" , "/images/cards/king_of_hearts2.png"));
        _deck.add(new CardBuche("Reine de coeur" , "/images/cards/queen_of_hearts2.png"));
        _deck.add(new CardAs("As de coeur" , "/images/cards/ace_of_hearts.png"));

        _deck.add(new CardBuche("10 de piques" , "/images/cards/10_of_spades.png"));
        _deck.add(new CardBuche("Valet de piques" , "/images/cards/jack_of_spades2.png"));
        _deck.add(new CardBuche("Roi de piques" , "/images/cards/king_of_spades2.png"));
        _deck.add(new CardBuche("Reine de piques" , "/images/cards/queen_of_spades2.png"));
        _deck.add(new CardAs("As de piques" , "/images/cards/ace_of_spades.png"));

        RandomizeDeck();

    }

    public void RandomizeDeck()
    {
        // Mélange le jeu de cartes
        for (int i = 0; i < 52; i++) {
            int randIndex = i + new Random().nextInt(52 - i);
            Card temp = _deck.get(i);
            _deck.set(i, _deck.get(randIndex));
            _deck.set(randIndex, temp);
        }
    }

    public Card Distribute()
    {
        return _deck.remove(0);
    }
}
