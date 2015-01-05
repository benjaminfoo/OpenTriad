package org.owls.opentriad.ui.customviews.delegates;

import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.ui.customviews.CardView;

/**
 * Created by Benni on 17.12.2014.
 */
public interface BattleFieldViewDelegates {

    public void onEmptyCardViewClick(CardView cardView, int row, int pos);

    public void onSetCardViewClick(CardView cardView, int row, int pos);

    public void onScoreChanged(Competitor owner, int newScore);

}
