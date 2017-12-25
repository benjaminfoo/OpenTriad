package de.bwulfert.engine.controller;

import de.bwulfert.engine.modell.Card;

import java.util.List;

public interface CardChooser {

    void setAvailableCards(List<Card> availableCards);

    List<Card> chooseCards();

}
