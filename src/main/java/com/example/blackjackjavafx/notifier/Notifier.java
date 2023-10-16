package com.example.blackjackjavafx.notifier;

import java.util.ArrayList;
import java.util.List;

public class Notifier<T> {
    private List<Listener<T>> listeners = new ArrayList<>();

    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener<T> listener) {
        listeners.remove(listener);
    }

    public void notify(T event) {
        for (Listener<T> listener : listeners) {
            listener.onEvent(event);
        }
    }
}
