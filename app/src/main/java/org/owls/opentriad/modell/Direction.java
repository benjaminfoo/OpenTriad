package org.owls.opentriad.modell;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * An enumeration of Directions which are used within the battle on the BattleField.
 */
public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction getOpposite(Direction direction){
        int directionValue = direction.ordinal();
        return Direction.values()[directionValue < 2 ? 2 + directionValue : -(2 - directionValue)];
    }
}
