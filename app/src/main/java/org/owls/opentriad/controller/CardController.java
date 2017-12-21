package org.owls.opentriad.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.owls.opentriad.OpenTriad;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.modell.Competitor;
import org.owls.opentriad.modell.Deck;
import org.owls.opentriad.modell.JsonCard;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    private List<JsonCard> cards = new LinkedList<JsonCard>();

    private Random r;

    public CardController(Context context) {
        this.context = context;
        r = new Random(System.currentTimeMillis());

        initializeCards();
    }

    private void initializeCards() {
        cards.clear();

        String json = null;
        try {
            InputStream is = context.getAssets().open("cards.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            Gson gson = new GsonBuilder().create();
            JsonCard[] data = gson.fromJson(json, JsonCard[].class);
            this.cards.addAll(Arrays.asList(data));

            List<String> cards = Arrays.asList(context.getAssets().list("cards"));

            for (JsonCard card : data) {
                Log.i("OpenTriad", card.toString());

                boolean b = cards.contains(card.getName().toLowerCase());
                Log.i("OpenTriad", "\t " + card.getName() + " within assets? " + b);
            }

            for (String card : cards) {
                card = card.replaceAll(".jpg", "");
                Log.i("OpenTriad", "Is " + card + " from asset-folder cards/ within json file? ---> ");
                for (JsonCard jsonCard : data) {
                    if (jsonCard.getName().equalsIgnoreCase(card)) {
                        Log.i("OpenTriad", "\t" + card + " is in json file, wuhu!");
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    public Card generateCard(Competitor owner) {

        /*
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
        }*/

        int iii = r.nextInt(cards.size());
        Log.i("OpenTriad", "random.nextInt: " + iii);
        Log.i("OpenTriad", "cards.size(): " + cards.size());
        JsonCard jsonCard = cards.get(iii);
        return new Card(jsonCard.getName(), jsonCard.getPowersArray(), owner);
    }

    public Deck generateDeck(Competitor owner) {
        List<Card> cards = new ArrayList<Card>(OpenTriad.DECK_MAX_CARD_COUNT);
        for (int index = 0; index < OpenTriad.DECK_MAX_CARD_COUNT; index++) {
            cards.add(generateCard(owner));
        }
        return new Deck(cards);
    }

}
