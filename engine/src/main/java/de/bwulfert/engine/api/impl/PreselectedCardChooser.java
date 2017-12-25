package de.bwulfert.engine.api.impl;

import de.bwulfert.engine.api.CardChooser;
import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

import java.util.ArrayList;
import java.util.List;

public class PreselectedCardChooser implements CardChooser {

    @Override
    public Deck chooseCards(List<Card> availableCards) {
        List<Card> cards = new ArrayList<>(5);
        cards.add(new Card("Test", 1, 2, 3, 4));
        cards.add(new Card("Test", 1, 2, 3, 4));
        cards.add(new Card("Test", 1, 2, 3, 4));
        cards.add(new Card("Test", 1, 2, 3, 4));
        cards.add(new Card("Test", 1, 2, 3, 4));
        return new Deck("Preselected Cards", cards);
    }

}
