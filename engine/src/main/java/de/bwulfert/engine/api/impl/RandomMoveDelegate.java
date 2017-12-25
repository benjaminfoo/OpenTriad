package de.bwulfert.engine.api.impl;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.api.MoveDelegate;
import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

import java.util.Random;

public class RandomMoveDelegate implements MoveDelegate {

    private Random random;
    private Deck deck;

    public RandomMoveDelegate() {
        random = new Random();
    }

    @Override
    public void setActiveDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Card getCard() {
        return deck.chooseAndRemove(0);
    }

    @Override
    public int getX() {
        return random.nextInt(OpenTriad.BATTLEFIELD_WIDTH);
    }

    @Override
    public int getY() {
        return random.nextInt(OpenTriad.BATTLEFIELD_HEIGHT);
    }

    @Override
    public Deck getActiveDeck() {
        return deck;
    }

}
