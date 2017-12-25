package de.bwulfert.engine.api;

import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

import java.util.List;

public interface CardChooser {

    Deck chooseCards(List<Card> availableCards);

}
