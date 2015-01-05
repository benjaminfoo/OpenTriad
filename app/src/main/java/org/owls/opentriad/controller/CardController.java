package org.owls.opentriad.controller;

import android.content.Context;

import org.owls.opentriad.R;
import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.modell.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The CardController class parses the /res/values/cards.xml for monster-names,
 * powers and name of graphic-files. It is able to generate a single card or a
 * complete deck.
 *
 * TODO: retrieve card information from database, not from localisation files.
 */
public class CardController {

    private static final String POWER_SEPERATOR = "-";
    private static final String PS = POWER_SEPERATOR;

    private Context context;

    private Random r;

    public CardController(Context context) {
        this.context = context;
        r = new Random(System.currentTimeMillis());
    }

    public Card generateCard(Competitor owner) {

        // 1. list card values from the drawable folder
        String[] rawCardData = context.getResources().getStringArray(R.array.level_one);

        // 2. get five valid entries

        // 3. parse those values and instantiate a new card entity
        String ent = rawCardData[r.nextInt(rawCardData.length)];

        // 4. parse the character name from the card entry
        String cardName = ent.substring(0, ent.indexOf(PS));
        String valueData = ent.substring(cardName.length() + 1, ent.length());

        // 5. parse the character powers from the card entry
        // power[0] - north
        // power[1] - eart
        // power[2] - south
        // power[3] - west
        int powers[] = new int[4];
        int i = 0;
        for (String value : valueData.split(PS)) {
            powers[i++] = Integer.parseInt(value);
        }

        return new Card(cardName, powers, owner);
    }

    public Deck generateDeck(Competitor owner) {
        List<Card> cards = new ArrayList<Card>(OpenTriad.DECK_MAX_CARD_COUNT);
        for (int index = 0; index < OpenTriad.DECK_MAX_CARD_COUNT; index++) {
            cards.add(generateCard(owner));
        }
        return new Deck(cards);
    }

}
