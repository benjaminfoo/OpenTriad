package de.bwulfert.engine.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private String name;
    private List<Card> cards;

    public Deck() {
        this.name = "Default Deck";
        this.cards = new ArrayList<>(5);
    }

    public Deck(String name) {
        this.name = name;
        this.cards = new ArrayList<>(5);
    }

    public Deck(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public Deck(String name, Card... cards) {
        this.name = name;
        this.cards = new ArrayList<>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Card chooseAndRemove(int nr) {
        Card currentcard = cards.get(nr);
        cards.remove(currentcard);
        return currentcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
