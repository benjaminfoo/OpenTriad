package org.owls.opentriad.modell;

import org.owls.opentriad.modell.delegates.DeckDelegates;

import java.util.List;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * A Deck is a set of Cards which enables a Competitor to choose from during his turn.
 * If a Deck is out of Cards, the game is over and a winner is determined, based on the Highscore
 * of the two Competitors.
 *
 * Its corresponding view is called DeckView
 *
 */
public class Deck {

    public List<Card> cards;

    private DeckDelegates deckDelegates;

    // we don't want to add cards into the deck during the game... :)
    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card remove(int location) {
        if (deckDelegates != null) {
            deckDelegates.onRemoveCard(location);
        }
        return cards.remove(location);
    }

    public boolean remove(Object object) {
        if (deckDelegates != null) {
            deckDelegates.onRemoveCard(object);
        }
        return cards.remove(object);
    }

    public int size() {
        return cards.size();
    }

    public Card get(int location) {
        return cards.get(location);
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
