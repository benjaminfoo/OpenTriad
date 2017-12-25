package de.bwulfert.engine.controller;

import de.bwulfert.engine.modell.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalCardChooser implements CardChooser {

    private List<Card> availableCards;
    private Scanner scanner;

    public TerminalCardChooser() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void setAvailableCards(List<Card> availableCards) {
        this.availableCards = availableCards;
    }

    private void printAvailableCards() {
        System.out.println("\nAvailable cars:");
        for (int i = 0; i < availableCards.size(); i++) {
            Card card = availableCards.get(i);
            System.out.println(i + ": " + card.getName());
        }
    }

    @Override
    public List<Card> chooseCards() {
        List<Card> cards = new ArrayList<>(5);
        while (cards.size() != 5) {
            printAvailableCards();
            System.out.println("Select a card:");
            int cardNr = scanner.nextInt();
            Card choosenCard = availableCards.get(cardNr);
            availableCards.remove(choosenCard);
            cards.add(choosenCard);
            System.out.println(choosenCard.getName() + " has been selected!");
        }

        System.out.println("You selected the following cards:");
        for (Card card : cards) {
            System.out.println("\t" + card.getName());
        }

        return cards;
    }
}
