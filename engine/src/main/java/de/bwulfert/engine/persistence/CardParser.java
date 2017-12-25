package de.bwulfert.engine.persistence;

import de.bwulfert.engine.model.Card;

import java.util.List;

public interface CardParser {

    public List<Card> parseCards(String path);

    public List<Card> parseCards();

}
