package com.example.blackjackjavafx.jeton;

public class Jeton
{
    public int get_value(){
        return _value;
    };
    private int _value;
    private String _imageURL;
    public Jeton(int value, String imageURL)
    {
        _value = value;
        _imageURL = imageURL;
    }

    public String get_imageURL() {
        return _imageURL;
    }

    public void set_imageURL(String imageURL) {
        this._imageURL = imageURL;
    }
}
