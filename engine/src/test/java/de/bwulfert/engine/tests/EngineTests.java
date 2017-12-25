package de.bwulfert.engine.tests;

import de.bwulfert.engine.OpenTriad;
import de.bwulfert.engine.api.impl.BattlefieldController;
import de.bwulfert.engine.api.impl.PlayerController;
import de.bwulfert.engine.api.impl.PreselectedCardChooser;
import de.bwulfert.engine.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTests {

    @Test
    public void testGameLoop() {
        OpenTriad openTriad = new OpenTriad();
        openTriad.loadCards();

        BattlefieldController battlefieldController = new BattlefieldController();
        battlefieldController.initialize(
                new PlayerController(new Player("Test Player 1", new PreselectedCardChooser().chooseCards(openTriad.getCards()))),
                new PlayerController(new Player("Test Player 2", new PreselectedCardChooser().chooseCards(openTriad.getCards())))
        );

        for (int i = 0; i < 9; i++) {
            battlefieldController.nextTurn();
        }

        assertEquals("Round count must be 9", 9, battlefieldController.getTurnCount());
    }

}
