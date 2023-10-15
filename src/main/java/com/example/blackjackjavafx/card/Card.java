package com.example.blackjackjavafx.card;

public class Card
{
    private String _cardName;
    private int _value;
    private String _imageURL;

    public String get_cardName() {
        return _cardName;
    }

    public int get_value() {
        return _value;
    }
    public String get_imageURL() {
        return _imageURL;
    }

    public Card(String name, int value, String imageURL )
    {
        _cardName = name;
        _value = value;
        _imageURL = imageURL;
    }


}
