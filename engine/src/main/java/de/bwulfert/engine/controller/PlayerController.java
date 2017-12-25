package de.bwulfert.engine.controller;

import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.modell.Player;

public class PlayerController {

    private Player player;
    private Card selectedCard;
    private int score;

    public PlayerController(Player player) {
        this.player = player;

        this.score = 5;
    }

    public void selectCard() {
        this.selectedCard = player.getDeck().getCards().get(0);
        this.player.getDeck().getCards().remove(0);
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

}
