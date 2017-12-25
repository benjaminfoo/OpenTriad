package de.bwulfert.engine;

import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.persistence.LocalCardParser;

import java.util.List;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 * <p>
 * The class which contains Triple Triad related constants,
 * for example the initial highscore each player has got after a game starts.
 */
public class OpenTriad {

    public static final String DEFAULT_ENCODING = "UTF-8";

    private List<Card> cards;

    public void loadCards() {
        cards = new LocalCardParser().parseCards();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
