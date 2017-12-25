package de.bwulfert.engine.tests;

import de.bwulfert.engine.model.Card;
import de.bwulfert.engine.persistence.LocalJsonParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardParserTests {

    @Test
    public void parseCards() {
        LocalJsonParser localJsonParser = new LocalJsonParser();
        List<Card> cards = localJsonParser.parseCards(getClass().getClassLoader().getResource("testCards.json").getFile());
        assertNotNull(cards);
    }

    @Test
    public void parseTestCards() {
        LocalJsonParser localJsonParser = new LocalJsonParser();
        List<Card> cards = localJsonParser.parseCards(getClass().getClassLoader().getResource("testCards.json").getFile());
        assertEquals("There are three cards in the test-set", cards.size(), 6);
    }

    @Test
    public void testFirstCard() {
        LocalJsonParser localJsonParser = new LocalJsonParser();
        List<Card> cards = localJsonParser.parseCards(getClass().getClassLoader().getResource("testCards.json").getFile());
        assertEquals(cards.get(0).getName(), "Test");
    }

}
