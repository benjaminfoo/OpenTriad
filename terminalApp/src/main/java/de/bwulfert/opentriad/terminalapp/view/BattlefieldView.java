package de.bwulfert.opentriad.terminalapp.view;

import de.bwulfert.engine.model.Battlefield;
import de.bwulfert.engine.model.Card;

import java.util.HashMap;
import java.util.Map;

public class BattlefieldView {

    private Battlefield battlefield;

    private Map<IntTupel, IntTupel> cardPivotPoints;
    private char[][] buffer;

    public static final int CARD_HEIGHT = 3;
    public static final int CARD_WIDTH = 3;

    public static final char GRID_CHAR = '#';

    public static final int GRID_WIDTH = 1;

    private static final int AMOUNT_OF_ROWS = 3;
    private static final int AMOUNT_OF_COLUMNS = 3;

    public BattlefieldView(Battlefield battlefield) {
        this.battlefield = battlefield;

        // 4 = Amount of grid lines (top, bottom, between cards)
        buffer = new char[4 + AMOUNT_OF_ROWS * CARD_HEIGHT][4 + AMOUNT_OF_COLUMNS * CARD_WIDTH];


        for (int y = 0; y < buffer.length; y++) {
            for (int x = 0; x < buffer[y].length; x++) {
                buffer[y][x] = ' ';
            }
        }

        // Generate local coordinate spaces where we'll be able to draw cards simply
        cardPivotPoints = new HashMap<>();
        for (int y = 0; y < CARD_HEIGHT; y++) {
            int yMap = GRID_WIDTH + (y * GRID_WIDTH) + (y * CARD_HEIGHT);
            for (int x = 0; x < CARD_WIDTH; x++) {
                int xMap = GRID_WIDTH + (x * GRID_WIDTH) + (x * CARD_WIDTH);
                cardPivotPoints.put(new IntTupel(y, x), new IntTupel(yMap, xMap));
            }
        }
    }

    private void renderBorder() {
        char[] topBorder = buffer[0];
        for (int x = 0; x < topBorder.length; x++) {
            topBorder[x] = GRID_CHAR;
        }

        char[] bottomBorder = buffer[buffer.length - 1];
        for (int x = 0; x < bottomBorder.length; x++) {
            bottomBorder[x] = GRID_CHAR;
        }

        // draw left border
        for (int y = 0; y < buffer.length; y++) {
            for (int x = 0; x < 1; x++) {
                buffer[y][x] = GRID_CHAR;
            }
        }

        // draw right border
        for (int y = 0; y < buffer.length; y++) {
            buffer[y][buffer[y].length - 1] = GRID_CHAR;
        }
    }

    private void renderHorizontalGridlines() {
        // draw line below first row of cards
        char[] firstGridLine = buffer[GRID_WIDTH + CARD_HEIGHT];
        for (int x = 0; x < firstGridLine.length; x++) {
            firstGridLine[x] = GRID_CHAR;
        }

        // draw line below second row of cards
        char[] secondGridline = buffer[GRID_WIDTH * 2 + CARD_HEIGHT * 2];
        for (int x = 0; x < secondGridline.length; x++) {
            secondGridline[x] = GRID_CHAR;
        }
    }


    private void renderVerticalGridLines() {
        // draw line next to the first card
        for (int y = 0; y < buffer.length; y++) {
            buffer[y][GRID_WIDTH + CARD_HEIGHT] = GRID_CHAR;
        }

        for (int y = 0; y < buffer.length; y++) {
            buffer[y][GRID_WIDTH * 2 + CARD_HEIGHT * 2] = GRID_CHAR;
        }
    }

    private void renderCard(Card card, int x, int y) {
        IntTupel gridPosition = cardPivotPoints.get(new IntTupel(y, x));

        // draw top power
        buffer[gridPosition.getY()][gridPosition.getX() + 1] = Character.forDigit(card.getTopPower(), 10);

        // draw left power
        buffer[gridPosition.getY() + 1][gridPosition.getX()] = Character.forDigit(card.getLeftPower(), 10);

        // draw right power
        buffer[gridPosition.getY() + 1][gridPosition.getX() + 2] = Character.forDigit(card.getRightPower(), 10);

        // draw bottom power
        buffer[gridPosition.getY() + 2][gridPosition.getX() + 1] = Character.forDigit(card.getBottomPower(), 10);

    }

    public void render() {
        renderBorder();
        renderVerticalGridLines();
        renderHorizontalGridlines();
        for (int y = 0; y < battlefield.getBattleField().length; y++) {
            for (int x = 0; x < battlefield.getBattleField()[y].length; x++) {
                if (battlefield.getBattleField()[y][x] != null) {
                    renderCard(battlefield.getBattleField()[y][x], y, x);
                }
            }
        }


        // render the buffer
        for (int y = 0; y < buffer.length; y++) {
            for (int x = 0; x < buffer[y].length; x++) {
                System.out.print(buffer[y][x]);
            }
            System.out.println();
        }
    }

}
