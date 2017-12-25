package de.bwulfert.engine.tests;

import de.bwulfert.engine.controller.BattlefieldController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTests {

    @Test
    public void testGameLoop() {
        BattlefieldController battlefieldController = new BattlefieldController();
        battlefieldController.initialize();

        for (int i = 0; i < 9; i++) {
            battlefieldController.nextTurn();
        }

        assertEquals("Round count must be 9", 9, battlefieldController.getTurnCount());
    }

}
