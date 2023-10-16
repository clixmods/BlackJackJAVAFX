package com.example.blackjackjavafx.gameState;

// An interface to be implemented by everyone interested in "Hello" events
public interface IGameStateListener {
    void onGameStateChanged(GameState newState, GameState oldState);
}
