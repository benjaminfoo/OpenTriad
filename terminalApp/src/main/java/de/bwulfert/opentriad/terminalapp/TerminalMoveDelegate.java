package de.bwulfert.opentriad.terminalapp;

import de.bwulfert.engine.controller.MoveDelegate;
import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.model.Deck;

import java.util.Scanner;

public class TerminalMoveDelegate implements MoveDelegate {

    private Deck deck;
    private Scanner scanner;

    public TerminalMoveDelegate() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setActiveDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Card getCard() {
        System.out.println("Select Card: ");
        return deck.chooseAndRemove(scanner.nextInt());
    }

    @Override
    public int getX() {
        System.out.println("Set X: ");
        return scanner.nextInt();
    }

    @Override
    public int getY() {
        System.out.println("Set Y: ");
        return scanner.nextInt();
    }

    @Override
    public Deck getActiveDeck() {
        return deck;
    }

}
