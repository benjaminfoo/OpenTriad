package de.bwulfert.engine;

import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;
import de.bwulfert.engine.persistence.LocalJsonParser;

import java.util.ArrayList;
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
        LocalJsonParser localJsonParser = new LocalJsonParser();
        cards = localJsonParser.parseCards(getClass().getClassLoader().getResource("cards.json").getFile());
    }

    public List<Card> getCards() {
        return cards;
    }

    // TODO: JEST FOR TESTING
    public Deck getDefaultDeck() {
        return new Deck("Default-Deck-1", new ArrayList<Card>(cards.subList(0, 4)));
    }

    public Deck getDefaultDeck2() {
        return new Deck("Default-Deck-2", new ArrayList<Card>(cards.subList(0, 4)));
    }
    // TODO: JEST FOR TESTING

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
