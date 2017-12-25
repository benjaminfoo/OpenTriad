package de.bwulfert.engine.controller;

import de.bwulfert.engine.modell.Battlefield;
import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.modell.Deck;
import de.bwulfert.engine.modell.Player;

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

    // TODO: REMOVE ME - JUST FOR TESTING, UNTIL REAL CARD-CHOOSING HAS BEEN IMPLEMENTED
    int x = 0;
    int y = 0;

    public void nextTurn() {
        // game loop
        if (!battlefield.areAllSlotsSet()) {
            // let the player make his turnCount
            currentPlayer.selectCard();

            // let the player set the selected card to a position
            battlefield.setCardAtPosition(currentPlayer.getPlayer(), currentPlayer.getSelectedCard(), x, y);

            // after every turnCount, switch the current player
            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
            battlefieldDelegate.onTurnFinished(this, firstPlayer, secondPlayer, ++turnCount);

            // let the player set the selected card to a position, because we're simply counting these values,
            // we've to make sure that the counting happens at the end of the turnCount ...
            // JUST FOR TESTING!
            if (x == 2) {
                x = 0;
                y++;
            } else {
                x++;
            }

            for (int y = 0; y < this.battlefield.getBattleField().length; y++) {
                for (int x = 0; x < this.battlefield.getBattleField()[y].length; x++) {
                    this.battlefield.setAllSlotsSet(this.battlefield.getBattleField()[y][x] != null);
                }
            }

            if (battlefield.areAllSlotsSet()) {
                battlefieldDelegate.onGameFinished(this, currentPlayer);
            }
        }
    }

    public void initialize() {
        // TODO: REMOVE ME - JUST FOR TESTING, UNTIL REAL CARD-CHOOSING HAS BEEN IMPLEMENTED
        firstPlayer = new PlayerController(new Player("Player 1", new Deck("Test-Deck",
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4)
        )));
        secondPlayer = new PlayerController(new Player("Player 2", new Deck("Test-Deck",
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4),
                new Card("Test", 1, 2, 3, 4)
        )));
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
