package org.owls.opentriad;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The class which contains Triple Triad related constants,
 * for example the initial highscore each player has got after a game starts.
 *
 */
public class OpenTriad {

    /**
     * The score value each player has got after the game starts
     */
    public final static int COMPETITORS_INITIAL_SCORE = 5;

    /**
     * The max. amount of cards for a Deck
     */
    public final static int DECK_MAX_CARD_COUNT = 5;

    /**
     * The max. amount of battlefield rows
     */
    public final static int BATTLEFIELD_MAX_ROW = 3;

    /**
     * The max. amount of battlefield columns
     */
    public final static int BATTLEFIELD_MAX_COLUMN = 3;

    /**
     * The maximum amount of cards for the BattleField.
     * It is a multiplication of the maximum amount of rows with the maximum amount of columns.
     */
    public final static int BATTLEFIELD_MAX_CARD_COUNT = BATTLEFIELD_MAX_ROW * BATTLEFIELD_MAX_COLUMN;

    /**
     * The amount of milliseconds used to wait for each AI-controlled game turn.
     * A value between 500 and 1500 is nice.
     * TODO: configurable with speed option in options screen?
     * TODO: implement option screen...
     */
    public final static int AI_TIME_PER_TURN = 750;

}
