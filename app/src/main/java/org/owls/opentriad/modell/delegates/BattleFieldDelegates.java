package org.owls.opentriad.modell.delegates;

import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;

/**
 * Created by Benni on 24.12.2014.
 */
public interface BattleFieldDelegates {

    /**
     * A delegate which gets called when a card within the BattleField gets changed
     *
     * @param row  - The y-coordinate within the BattleField
     * @param pos  - The x-coordinate within the BattleField
     * @param card - The Card which has been set on the given row and pos
     */
    public void onBattleFieldUpdate(int row, int pos, Card card);

    /**
     */
    public void onScoreChanged(Competitor owner, int newScore);

}
