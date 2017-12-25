package de.bwulfert.engine.api.impl;

import de.bwulfert.engine.api.MoveDelegate;
import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

public class DefaultMoveDelegate implements MoveDelegate {

    // TODO: REMOVE ME - JUST FOR TESTING, UNTIL REAL CARD-CHOOSING HAS BEEN IMPLEMENTED
    public static int x = 0;
    public static int y = 0;
    // Remember - these values are shared across all DefaultMoveDelegate-Instances

    private Deck deck;

    @Override
    public void setActiveDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Card getCard() {
        // TODO: REMOVE ME - JUST FOR TESTING, UNTIL REAL CARD-CHOOSING HAS BEEN IMPLEMENTED

        // TODO: REMOVE ME - JUST FOR TESTING, UNTIL REAL CARD-CHOOSING HAS BEEN IMPLEMENTED

        return deck.chooseAndRemove(0);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Deck getActiveDeck() {
        return deck;
    }

}
