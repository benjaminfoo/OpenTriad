package de.bwulfert.opentriad.terminalapp;

import de.bwulfert.engine.controller.BattlefieldController;
import de.bwulfert.engine.controller.BattlefieldDelegate;
import de.bwulfert.engine.controller.PlayerController;

public class OpenTriadTerminal implements BattlefieldDelegate {

    public OpenTriadTerminal() {
        BattlefieldController battlefieldController = new BattlefieldController(this);
        battlefieldController.initialize();

        while (!battlefieldController.getBattlefield().areAllSlotsSet()) {
            battlefieldController.nextTurn();
        }


    }

    @Override
    public void onGameStarted(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer) {
        System.out.println("First player: " + firstPlayer.getPlayer().getName());
        System.out.println("Second player: " + secondPlayer.getPlayer().getName());
    }

    @Override
    public void onTurnFinished(BattlefieldController battlefieldController, PlayerController currentPlayer, PlayerController nextPlayer, int turnCount) {

    }

    @Override
    public void onGameFinished(BattlefieldController battlefieldController, PlayerController winner) {
        System.out.println("Winner: " + winner.getPlayer().getName());
    }

    public static void main(String[] args) {
        new OpenTriadTerminal();
    }

}