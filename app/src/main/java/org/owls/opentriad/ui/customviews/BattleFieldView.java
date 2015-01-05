package org.owls.opentriad.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import org.owls.opentriad.R;
import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.modell.BattleField;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.modell.delegates.BattleFieldDelegates;
import org.owls.opentriad.ui.customviews.delegates.BattleFieldViewDelegates;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The BattleFieldView is used to display the state of the BattleField.
 * It contains an array of CardViews which are controlled by the competitors of each game.
 *
 * Its layout is called R.layout.view_battlefield
 */
public class BattleFieldView extends RelativeLayout implements View.OnClickListener, BattleFieldDelegates {

    // the layout which holds a 3x3 card matrix
    // the y position is a ViewContainer of class TableRow
    // the x position is the main ImageView which contains the card graphic.
    // the corresponding views for the cards
    private CardView[] cardViews;

    // the modell which data is displayed :)
    public BattleField battleField;

    private BattleFieldViewDelegates battleFieldViewDelegates;

    public BattleFieldView(Context context) {
        super(context);
        init();
    }

    public BattleFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BattleFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        // initialize the BattleField and setup the listeners
        battleField = new BattleField(this);

        inflate(getContext(), R.layout.view_battlefield, this);

        cardViews = new CardView[]{
                (CardView) findViewById(R.id.cardboard_r0_p0),
                (CardView) findViewById(R.id.cardboard_r0_p1),
                (CardView) findViewById(R.id.cardboard_r0_p2),
                (CardView) findViewById(R.id.cardboard_r1_p0),
                (CardView) findViewById(R.id.cardboard_r1_p1),
                (CardView) findViewById(R.id.cardboard_r1_p2),
                (CardView) findViewById(R.id.cardboard_r2_p0),
                (CardView) findViewById(R.id.cardboard_r2_p1),
                (CardView) findViewById(R.id.cardboard_r2_p2),
        };

        for (CardView cardView : cardViews) {
            cardView.setOnClickListener(this);
        }

        cardViews[0].setTag("r0c0");
        cardViews[1].setTag("r0c1");
        cardViews[2].setTag("r0c2");
        cardViews[3].setTag("r1c0");
        cardViews[4].setTag("r1c1");
        cardViews[5].setTag("r1c2");
        cardViews[6].setTag("r2c0");
        cardViews[7].setTag("r2c1");
        cardViews[8].setTag("r2c2");

    }

    @Override
    public void onClick(View v) {
        CardView clickedCardView = (CardView) v;

        if (battleFieldViewDelegates != null) {

            // the raw position data is formatted like: r0c0, .. r1c2 .. , r2c2
            String rawPosData = clickedCardView.getTag().toString();
            int row = Integer.parseInt(rawPosData.substring(1, 2));
            int pos = Integer.parseInt(rawPosData.substring(3));

            if (clickedCardView.isEmpty()) {
                battleFieldViewDelegates.onEmptyCardViewClick(clickedCardView, row, pos);
            } else {
                battleFieldViewDelegates.onSetCardViewClick(clickedCardView, row, pos);
            }
        }
    }

    public void setBattleFieldViewDelegates(BattleFieldViewDelegates battleFieldViewDelegates) {
        this.battleFieldViewDelegates = battleFieldViewDelegates;
    }

    // this is where the card just gets set, the battle logic is already executed so no battle here
    @Override
    public void onBattleFieldUpdate(int row, int pos, Card card) {
        cardViews[(row * OpenTriad.BATTLEFIELD_MAX_ROW) + pos].setCard(card);
    }

    @Override
    public void onScoreChanged(Competitor owner, int newScore) {
        if(battleFieldViewDelegates != null){
            battleFieldViewDelegates.onScoreChanged(owner, newScore);
        }
    }

    public boolean positionAlreadyInUse(int row, int pos) {
        return battleField.positionAlreadyInUse(row, pos);
    }

    public boolean allSlotsSet() {
        return battleField.allSlotsSet();
    }
}
