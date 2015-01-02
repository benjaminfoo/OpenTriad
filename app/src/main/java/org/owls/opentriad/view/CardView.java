package org.owls.opentriad.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import org.owls.opentriad.modell.Card;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The CardView is the corresponding view of a Card.
 *
 */
public class CardView extends ImageView {

    // the corresponding card entity
    private Card card;

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // TODO: we don't want to reload the resources every time, instead, we wan't to reuse the
    // TODO: - already inflated bitmaps from the memory
    public void setCard(Card card) {
        this.card = card;

        int cardResourceIdentifier = getResources().getIdentifier(card.name, "drawable", getContext().getPackageName());
        Drawable drawable = getResources().getDrawable(cardResourceIdentifier);
        setImageDrawable(drawable);
        invalidate();
    }

    public Card getCard() {
        return card;
    }

    public boolean isEmpty() {
        return card == null;
    }

}
