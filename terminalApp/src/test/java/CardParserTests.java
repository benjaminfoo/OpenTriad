import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.persistence.LocalCardParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardParserTests {

    @Test
    public void parseCards() {
        LocalCardParser localCardParser = new LocalCardParser();
        List<Card> cards = localCardParser.parseCards(getClass().getClassLoader().getResource("testCards.json").getFile());
        assertNotNull(cards);
    }

    @Test
    public void parseTestCards() {
        LocalCardParser localCardParser = new LocalCardParser();
        List<Card> cards = localCardParser.parseCards(getClass().getClassLoader().getResource("cards.json").getFile());
        assertEquals("There are three cards in the test-set", cards.size(), 6);
    }

    @Test
    public void testFirstCard() {
        LocalCardParser localCardParser = new LocalCardParser();
        List<Card> cards = localCardParser.parseCards(getClass().getClassLoader().getResource("testCards.json").getFile());
        assertEquals(cards.get(0).getName(), "Test");
    }

}
