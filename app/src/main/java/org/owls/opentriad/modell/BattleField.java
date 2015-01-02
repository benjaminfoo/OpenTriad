package org.owls.opentriad.modell;

import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.modell.delegates.BattleFieldDelegates;

import java.util.HashMap;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * A BattleField contains the cards which are in battle and not more on the "hand" of a player.
 * It is responsible for computing the winning card in battles and finding 'neighbors'.
 * Neighbors are cards on the BattleField next to the card which has been set by a Competitor
 * on its current turn.
 *
 * Its corresponding view is called BattleFieldView
 *
 * TODO: mimic original Final Fantasy 8 HighScore behaviour
 */
public class BattleField {

    public Card[] cards;

    private BattleFieldDelegates battleFieldDelegates;

    public BattleField(BattleFieldDelegates battleFieldDelegates) {
        this.battleFieldDelegates = battleFieldDelegates;

        cards = new Card[OpenTriad.BATTLEFIELD_MAX_CARD_COUNT];
    }

    public void setCard(int row, int pos, Card card) {
        if (battleFieldDelegates != null) {
            // set the card
            cards[(row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos] = card;
            battleFieldDelegates.onBattleFieldUpdate(row, pos, card);

            // detect the neighbors of the set card
            HashMap<Direction, Card> neighbors = getNeighbors(row, pos);

            // battleFieldDelegates.onScoreChanged(card.owner, ++card.owner.score);

            // let the battle begin!
            for (Direction direction : neighbors.keySet()) {
                Card neighbor = neighbors.get(direction);

                    if(card.getPowerByDirection(direction) > neighbor.getPowerByDirection(direction.getOpposite(direction))){
                        if(neighbor.owner != card.owner){
                            if (battleFieldDelegates != null) {
                                battleFieldDelegates.onScoreChanged(card.owner, ++card.owner.score);
                                battleFieldDelegates.onScoreChanged(neighbor.owner, --neighbor.owner.score);
                                neighbor.owner = card.owner;
                                break; // we only want to fight our first neighbor... this isn't correct
                            }
                        }
                    }
            }
        }
    }

    public void setBattleFieldDelegates(BattleFieldDelegates battleFieldDelegates) {
        this.battleFieldDelegates = battleFieldDelegates;
    }

    public HashMap<Direction, Card> getNeighbors(int row, int pos){
        HashMap<Direction, Card> neighbors = new HashMap<Direction, Card>();

        // TODO: translate method description
        // Gegeben sind row und pos parameter, welche die y und x koordinaten innerhalb des
        // spielfeldes darstellen.
        //
        // - Der Karten-Array wird immer von links oben nach rechts unten iteriert
        //
        // - Ist eine Karte bspw. im Spielfeld-Zentrum (row = pos = 1), dann besitzt diese
        //   vier potenzielle Nachbarn.
        //
        // - Ist eine Karte in der ersten Reihe, so wird diese niemals einen Nachbarn besitzen
        //   welcher positionell über ihr steht.
        //
        // - Ist eine Karte in der letzten Reihe, so wird diese niemals einen Nachbarn besitzen
        //   welcher positionell unter ihr steht.
        //
        // - Ist eine Karte in der ersten Position einer Reihe, so wird diese niemals einen Nachbarn
        // besitzen welcher positionell links von ihr steht.
        //
        // - Ist eine Karte in der letzten Position einer Reihe, so wird diese niemals einen Nachbarn
        // besitzen welcher positionell rechts von ihr steht.
        //
        // 2D -> 1D mapping = (row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos)
        // TODO: translate method description

        // row neighbor handling
        if(row != 0){
            Card card = getCard(row - 1, pos);
            if (card != null)
                neighbors.put(Direction.NORTH, card);
        }

        if(row != OpenTriad.BATTLEFIELD_MAX_ROW - 1 /*we're working with arrays here... */){
            Card card = getCard(row + 1, pos);
            if (card != null)
                neighbors.put(Direction.SOUTH, card);
        }
        // row neighbor handling

        // pos neighbor handling
        if(pos != 0){
            Card card = getCard(row, pos - 1);
            if (card != null)
                neighbors.put(Direction.WEST, card);
        }

        if(pos != OpenTriad.BATTLEFIELD_MAX_ROW - 1){
            Card card = getCard(row, pos + 1);
            if (card != null)
                neighbors.put(Direction.EAST, card);
        }
        // pos neighbor handling


        return neighbors;
    }

    public Card getCard(int row, int pos){
        return cards[twoDimToOne(row, pos)];
    }

    public int twoDimToOne(int row, int pos){
        return (row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos;
    }

    public boolean positionAlreadyInUse(int row, int pos){
        return cards[(row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos] != null;
    }

    public boolean allSlotsSet(){
        boolean allSet = true;
        for (Card card : cards) {
            allSet &= card != null;
        }
        return allSet;
    }


}
