package de.bwulfert.engine.controller;

import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.modell.Deck;

import java.util.List;

public interface CardChooser {

    Deck chooseCards(List<Card> availableCards);

}
