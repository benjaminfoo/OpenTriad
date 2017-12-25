package de.bwulfert.opentriad.terminalapp;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.controller.*;
import de.bwulfert.engine.modell.Player;

public class OpenTriadTerminal implements BattlefieldDelegate {

    public OpenTriadTerminal() {
        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();
        BattlefieldController battlefieldController = new BattlefieldController(this);
        battlefieldController.initialize(
                new PlayerController(new Player("Test Player 1", new TerminalCardChooser().chooseCards(openTriad.getCards()))),
                new PlayerController(new Player("Test Player 2", new PreselectedCardChooser().chooseCards(openTriad.getCards())))
        );

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