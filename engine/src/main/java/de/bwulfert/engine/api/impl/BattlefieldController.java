package de.bwulfert.engine.api.impl;

import de.bwulfert.engine.api.BattlefieldDelegate;
import de.bwulfert.engine.api.MoveDelegate;
import de.bwulfert.engine.model.Battlefield;
import de.bwulfert.engine.model.Card;

public class BattlefieldController {

    private int turnCount = 0;

    private PlayerController firstPlayer;
    private PlayerController secondPlayer;
    private PlayerController currentPlayer;

    // The entity where the actual gameplay happens
    private Battlefield battlefield;

    private BattlefieldDelegate battlefieldDelegate;

    public BattlefieldController() {
        this.battlefieldDelegate = new DefaultBattlefieldDelegate();
    }

    public BattlefieldController(BattlefieldDelegate battlefieldDelegate) {
        this.battlefieldDelegate = battlefieldDelegate;
    }

    public void nextTurn() {
        // game loop
        if (!battlefield.areAllSlotsSet()) {

            // let the player set the selected card to a position
            MoveDelegate moveDelegate = currentPlayer.getMoveDelegate();
            Card card = moveDelegate.getCard();
            int xPosition = moveDelegate.getX();
            int yPosition = moveDelegate.getY();

            while (!battlefield.isSlotAvailable(xPosition, yPosition)) {
                battlefieldDelegate.slotAlreadySet(xPosition, yPosition);
                xPosition = moveDelegate.getX();
                yPosition = moveDelegate.getY();
            }

            battlefield.setCardAtPosition(
                    currentPlayer.getPlayer(),
                    card,
                    xPosition,
                    yPosition
            );

            // after every turnCount, switch the current player
            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
            battlefieldDelegate.onTurnFinished(this, firstPlayer, secondPlayer, ++turnCount);

            // let the player set the selected card to a position, because we're simply counting these values,
            // we've to make sure that the counting happens at the end of the turnCount ...
            boolean areAllSlotsSet = true;
            for (int y = 0; y < this.battlefield.getBattleField().length; y++) {
                for (int x = 0; x < this.battlefield.getBattleField()[y].length; x++) {
                    areAllSlotsSet &= this.battlefield.getBattleField()[y][x] != null;
                }
            }
            this.battlefield.setAllSlotsSet(areAllSlotsSet);

            // inform everyone that the game is finished
            if (battlefield.areAllSlotsSet()) {
                battlefieldDelegate.onGameFinished(this, currentPlayer);
            }
        }
    }

    public void initialize(PlayerController firstPlayer, PlayerController secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        battlefield = new Battlefield();

        // init phase
        currentPlayer = firstPlayer;

        battlefieldDelegate.onGameStarted(this, firstPlayer, secondPlayer);
    }

    public int getTurnCount() {
        return turnCount;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }
}
