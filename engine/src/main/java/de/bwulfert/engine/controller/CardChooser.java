package de.bwulfert.engine.controller;

import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

import java.util.List;

public interface CardChooser {

    Deck chooseCards(List<Card> availableCards);

}
