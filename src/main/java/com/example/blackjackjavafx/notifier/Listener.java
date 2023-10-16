package com.example.blackjackjavafx.notifier;

public interface Listener<T> {
    void onEvent(T event);
}