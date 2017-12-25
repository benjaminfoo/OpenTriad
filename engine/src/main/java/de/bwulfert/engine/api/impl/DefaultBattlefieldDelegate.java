package de.bwulfert.engine.api.impl;

import de.bwulfert.engine.api.BattlefieldDelegate;

public class DefaultBattlefieldDelegate implements BattlefieldDelegate {

    @Override
    public void onGameStarted(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer) {

    }

    @Override
    public void onTurnFinished(BattlefieldController battlefieldController, PlayerController currentPlayer, PlayerController nextPlayer, int roundCount) {

    }

    @Override
    public void slotAlreadySet(int xPosition, int yPosition) {

    }

    @Override
    public void onGameFinished(BattlefieldController battlefieldController, PlayerController winner) {

    }

}
