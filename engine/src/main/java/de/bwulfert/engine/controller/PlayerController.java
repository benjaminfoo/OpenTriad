package de.bwulfert.engine.controller;

import de.bwulfert.engine.model.Player;

public class PlayerController {

    private Player player;
    private int score;
    private MoveDelegate moveDelegate;

    public PlayerController(Player player, MoveDelegate moveDelegate) {
        this.player = player;
        this.score = 5;
        this.moveDelegate = moveDelegate;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public MoveDelegate getMoveDelegate() {
        return moveDelegate;
    }
}
