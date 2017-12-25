package de.bwulfert.engine.tests;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.api.MoveDelegate;
import de.bwulfert.engine.api.impl.BattlefieldController;
import de.bwulfert.engine.api.impl.PlayerController;
import de.bwulfert.engine.api.impl.PreselectedCardChooser;
import de.bwulfert.engine.api.impl.RandomMoveDelegate;
import de.bwulfert.engine.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTests {

    @Test
    public void testGameLoop() {
        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();

        BattlefieldController battlefieldController = new BattlefieldController();
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

        for (int i = 0; i < 9; i++) {
            battlefieldController.nextTurn();
        }

        assertEquals("Round count must be 9", 9, battlefieldController.getTurnCount());
    }

}
