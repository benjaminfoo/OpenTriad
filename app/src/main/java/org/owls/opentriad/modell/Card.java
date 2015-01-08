package org.owls.opentriad.modell;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * A Card is the main focused entity in the Triple Triad game.
 * It contains a name and a set of powers (int-values).
 *
 * After a Card is set on the BattleField, the card-neighbors are calculated.
 * After the neighbors are found, a "battle" is started in which the card is the winner if the
 * power in a given direction (look at comments) is higher than the power of a neighbor card.
 *
 * Its corresponding view is called CardView
 *
 */
public class Card {

    public String name;

    // the cards powers
    // Index - Side NESW
    // power[0] - north
    // power[1] - east
    // power[2] - south
    // power[3] - west
    public int[] powers;

    public Competitor owner;

    public Card(String name, int[] powers, Competitor owner) {
        this.name = name;
        this.powers = powers;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return name + " (" + getPowerSum() + ")";
    }

    public String getPowers() {
        return powers[0] + ", " + powers[1] + ", " + powers[2] + ", " + powers[3];
    }

    public int getPowerSum(){
        return powers[0] + powers[1] + powers[2] + powers[3];
    }

    public int getPowerByDirection(Direction direction){
        return powers[direction.ordinal()];
    }
}
