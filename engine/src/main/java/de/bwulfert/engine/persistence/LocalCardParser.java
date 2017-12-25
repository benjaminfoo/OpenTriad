package de.bwulfert.engine.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bwulfert.engine.modell.Card;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.bwulfert.engine.OpenTriad.DEFAULT_ENCODING;

public class LocalCardParser implements CardParser {

    public List<Card> parseCards(String path) {
        List<Card> cards = new ArrayList<>();
        String json = null;
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, DEFAULT_ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            Gson gson = new GsonBuilder().create();
            Card[] data = gson.fromJson(json, Card[].class);
            cards.addAll(Arrays.asList(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }

    public List<Card> parseCards() {
        return parseCards("/cards.json");
    }

}
