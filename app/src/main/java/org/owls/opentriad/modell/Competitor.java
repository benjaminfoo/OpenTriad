package org.owls.opentriad.modell;

import org.owls.opentriad.OpenTriad;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The Competitor is one "player" of the game.
 * There are two Competitors in each game, either controlled by human or AI.
 *
 * A Competitor has a name which is displayed during the start or the end of his turn,
 * a boolean which determines if its controlled by the AI or a competitor,
 * a score which is the total amount of wins / looses during a battle,
 * a deck which is a set of cards and another boolean which determines if the instance of a
 * competitor object is actively playing (setting a card on the battleField) or waiting for the
 * opponent to set a card on the battleField.
 */
public class Competitor {

    // the name of the competitor
    // TODO: implement settings/profile activity for setting this name
    public String name = "Competitor ";

    // is the player a bot or maybe another player?
    // TODO: implement multiplayer ?
    public boolean isBot = false;

    // the score of the current player
    public int score = OpenTriad.COMPETITORS_INITIAL_SCORE;

    // the deck of cards which the competitor uses to compete
    public Deck deck;

    // determines whether this competitor is actively playing
    public boolean active;

    public Competitor(String name, Deck deck) {
        this.deck = deck;
        this.name = name;

        this.active = false;
    }

}
