package de.bwulfert.engine.controller;

import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

public interface MoveDelegate {

    // The set of cards a player can choose from every turn
    void setActiveDeck(Deck deck);

    // The card which a player has choosen to be played in his current move
    Card getCard();

    // The x position of the players card on the battlefield
    int getX();

    // The y position of the players card on the battlefield
    int getY();

    Deck getActiveDeck();

}
