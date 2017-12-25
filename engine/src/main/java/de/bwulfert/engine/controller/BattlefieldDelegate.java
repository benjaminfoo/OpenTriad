package de.bwulfert.engine.controller;

public interface BattlefieldDelegate {

    void onGameStarted(BattlefieldController battlefieldController, PlayerController firstPlayer, PlayerController secondPlayer);

    void onTurnFinished(BattlefieldController battlefieldController, PlayerController currentPlayer, PlayerController nextPlayer, int turnCount);

    void onGameFinished(BattlefieldController battlefieldController, PlayerController winner);

}
