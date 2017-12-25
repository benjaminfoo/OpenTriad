package de.bwulfert.opentriad.terminalapp;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.api.BattlefieldDelegate;
import de.bwulfert.engine.api.MoveDelegate;
import de.bwulfert.engine.api.impl.BattlefieldController;
import de.bwulfert.engine.api.impl.PlayerController;
import de.bwulfert.engine.api.impl.PreselectedCardChooser;
import de.bwulfert.engine.api.impl.RandomMoveDelegate;
import de.bwulfert.engine.model.Player;
import de.bwulfert.opentriad.terminalapp.view.BattlefieldView;

import java.util.Arrays;

public class OpenTriadTerminal implements BattlefieldDelegate {

    BattlefieldView battlefieldView;

    public OpenTriadTerminal() {
        System.out.println("Welcome to OpenTriad Terminal-Edition!");

        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();
        BattlefieldController battlefieldController = new BattlefieldController(this);

        MoveDelegate playerMoveDelegate = new RandomMoveDelegate();
        playerMoveDelegate.setActiveDeck(new PreselectedCardChooser().chooseCards(openTriad.getCards()));

        MoveDelegate cpuMoveDelegate = new RandomMoveDelegate();
        cpuMoveDelegate.setActiveDeck(new PreselectedCardChooser().chooseCards(openTriad.getCards()));
        battlefieldController.initialize(
                new PlayerController(
                        new Player("Test Player 1"),
                        playerMoveDelegate
                ),
                new PlayerController(
                        new Player("Test Player 2"),
                        cpuMoveDelegate
                )
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
        System.out.println("\n\n");
        battlefieldView.render();
        System.out.println(currentPlayer.getPlayer().getName() + " - Score: " + currentPlayer.getScore());
        System.out.println("\tDeck: " + Arrays.asList(currentPlayer.getMoveDelegate().getActiveDeck()));
        System.out.println(nextPlayer.getPlayer().getName() + " - Score: " + nextPlayer.getScore());
        System.out.println("\tDeck: " + Arrays.asList(nextPlayer.getMoveDelegate().getActiveDeck()));
        System.out.println("\n\n");
    }

    @Override
    public void slotAlreadySet(int xPosition, int yPosition) {
        System.out.println("Slot at x: " + xPosition + " | y:" + yPosition + " already set, you've to use another one.");
    }

    @Override
    public void onGameFinished(BattlefieldController battlefieldController, PlayerController winner) {
        System.out.println("Winner: " + winner.getPlayer().getName());
    }

    public static void main(String[] args) {
        new OpenTriadTerminal();
    }

}