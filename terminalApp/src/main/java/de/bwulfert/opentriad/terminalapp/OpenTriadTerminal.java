package de.bwulfert.opentriad.terminalapp;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.controller.*;
import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.modell.Player;
import de.bwulfert.opentriad.terminalapp.view.BattlefieldView;

import java.util.Arrays;

public class OpenTriadTerminal implements BattlefieldDelegate {

    BattlefieldView battlefieldView;

    public OpenTriadTerminal() {
        System.out.println("Welcome to OpenTriad Terminal-Edition!");

        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();
        BattlefieldController battlefieldController = new BattlefieldController(this);
        battlefieldController.initialize(
                new PlayerController(new Player("Test Player 1", new TerminalCardChooser().chooseCards(openTriad.getCards()))),
                new PlayerController(new Player("Test Player 2", new PreselectedCardChooser().chooseCards(openTriad.getCards())))
        );

        battlefieldView = new BattlefieldView(battlefieldController.getBattlefield());

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
        System.out.println();
        Card[][] battleField = battlefieldController.getBattlefield().getBattleField();
        battlefieldView.render();
        System.out.println("Player 1 - " + currentPlayer.getPlayer().getName() + " - Score: " + currentPlayer.getScore());
        System.out.println("Cards: " + Arrays.asList(currentPlayer.getPlayer().getDeck().getCards()));
        System.out.println("Player 2 - " + nextPlayer.getPlayer().getName() + " - Score: " + nextPlayer.getScore());
        System.out.println("Cards: " + Arrays.asList(nextPlayer.getPlayer().getDeck().getCards()));
    }

    @Override
    public void onGameFinished(BattlefieldController battlefieldController, PlayerController winner) {
        System.out.println("Winner: " + winner.getPlayer().getName());
    }

    public static void main(String[] args) {
        new OpenTriadTerminal();
    }

}