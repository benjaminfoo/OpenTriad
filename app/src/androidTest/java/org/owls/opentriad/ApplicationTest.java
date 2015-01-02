package org.owls.opentriad;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.owls.opentriad.controller.CardController;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }

    @SmallTest
    public Card testGenerateCard() {
        Card card = new CardController(this.getContext()).generateCard();
        assertNotNull(card);
        System.out.println(card.toString());
        return card;
    }

    @SmallTest
    public Competitor testGenerateCompetitor() {
        Competitor competitor = new Competitor(false, cardController.generateDeck(opponent));

        for (int i = 0; i < competitor.deck.length; i++) {
            competitor.deck[i] = testGenerateCard();
        }

        assertNotNull(competitor);
        assertNotNull(competitor.deck);
        assertTrue(competitor.deck.length == 5);

        return competitor;
    }

}