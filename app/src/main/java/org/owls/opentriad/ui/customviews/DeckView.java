package org.owls.opentriad.ui.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.R;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.ui.customviews.delegates.DeckViewDelegates;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The DeckView is the corresponding view of a Deck.
 *
 * A DeckView is a vertically aligned layout container which has got Set of Cards.
 * Each DeckView represents a the hand of either the "player" or the opponent.
 *
 * If a DeckView is used to display the opponents hand, it only displays an image
 * of a Triple Triad card background.
 *
 */
public class DeckView extends LinearLayout implements View.OnClickListener {

    private List<CardView> cardViews;

    private Competitor competitor;

    private DeckViewDelegates deckViewDelegates;

    // is this the view the player should be able to control or not?
    // if this isn't the view, disable all the cards of it and
    // display ui_card_background.jpg instead.
    private boolean foreignDeck;

    // used to determine the last clicked image for scaling fx
    private CardView lastClicked;

    public DeckView(Context context) {
        super(context);
        init();
    }

    public DeckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void initializePlayerData(Competitor competitor) {
        this.competitor = competitor;

        if (foreignDeck) {
            for (int i = 0; i < cardViews.size(); i++) {
                String cardToLoad = competitor.deck.get(i).name;
                try {
                    InputStream is = getContext().getAssets().open("cards/r" + cardToLoad.toLowerCase() + ".jpg");
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    cardViews.get(i).setImageBitmap(bitmap);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                updateIndexPerCardAsTag();
                cardViews.get(i).setOnClickListener(this);
            }
        } else {
            for (CardView cardView : cardViews) {
                cardView.setImageDrawable(getResources().getDrawable(R.drawable.ui_card_background));
            }
        }

    }

    public void updateIndexPerCardAsTag() {
        for (int i = 0; i < cardViews.size(); i++) {
            CardView cardView = cardViews.get(i);
            cardView.setTag(i);
            cardView.invalidate();
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_deck, this);

        cardViews = new ArrayList<CardView>(OpenTriad.DECK_MAX_CARD_COUNT);
        cardViews.add((CardView) findViewById(R.id.deck_card_1));
        cardViews.add((CardView) findViewById(R.id.deck_card_2));
        cardViews.add((CardView) findViewById(R.id.deck_card_3));
        cardViews.add((CardView) findViewById(R.id.deck_card_4));
        cardViews.add((CardView) findViewById(R.id.deck_card_5));

        foreignDeck = getTag().toString().equals("player");

    }


    @Override
    public void onClick(View cardView) {
        if (competitor.active) {
            updateIndexPerCardAsTag();
            if (cardView != null && cardView.getVisibility() == VISIBLE) {
                int i = (Integer) cardView.getTag();
                setActiveCard(i);
            }
        }
    }

    public void setActiveCard(int index) {

        // if the index equals -1, its time to reset the activeCardState to NOTHIN'
        if (lastClicked != null && index == -1) {
            lastClicked.setScaleX(1);
            lastClicked.setScaleY(1);
            lastClicked = null;
            return;
        }

        // set the reference from the view to the card
        competitor.deck.get(index).owner = competitor;
        cardViews.get(index).setCard(competitor.deck.get(index));

        if (lastClicked != null) {
            lastClicked.setScaleX(1);
            lastClicked.setScaleY(1);
        }

        cardViews.get(index).setScaleX(2);
        cardViews.get(index).setScaleY(2);

        setLastClicked(cardViews.get(index));

        if (deckViewDelegates != null) {
            deckViewDelegates.activeCardSet(competitor.deck.get(index));
        }
    }


    // TODO: this function is way to complicated and bad!
    public Card useActiveCard() {
        updateIndexPerCardAsTag();
        // create a reference to the lastClickedView, because we wan't to remove it from the
        // collection AND return it :) - i dont know!
        CardView lastClickedView = lastClicked;

        // remove the card from the competitors deck
        competitor.deck.remove(lastClickedView.getCard());

        // NULLify the last clicked card, because after the card has been set on the battlefield,
        // it has to be removed from the deck
        lastClicked.setVisibility(GONE);
        setActiveCard(-1);

        int tag = ((Integer) lastClickedView.getTag()).intValue();
        cardViews.remove(tag);

        // the card which gets set on the battleField
        return lastClickedView.getCard();
    }

    public boolean hasActiveCard() {
        return lastClicked != null;
    }

    public void setLastClicked(CardView lastClicked) {
        this.lastClicked = lastClicked;
    }

    public void setDeckViewDelegates(DeckViewDelegates deckViewDelegates) {
        this.deckViewDelegates = deckViewDelegates;
    }

    public boolean hasCardsLeft() {
        return competitor.deck.size() > 0;
    }
}
