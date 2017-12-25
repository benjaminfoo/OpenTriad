package de.bwulfert.engine.api;

import de.bwulfert.engine.api.impl.BattlefieldController;
import de.bwulfert.engine.api.impl.PlayerController;

public interface BattlefieldDelegate {

    void onGameStarted(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer);

    void onTurnFinished(BattlefieldController battlefieldController, PlayerController currentPlayer, PlayerController nextPlayer, int turnCount);

    void slotAlreadySet(int xPosition, int yPosition);

    void onGameFinished(BattlefieldController battlefieldController, PlayerController winner);

}
