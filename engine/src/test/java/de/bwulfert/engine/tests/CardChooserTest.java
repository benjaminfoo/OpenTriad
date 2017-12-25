package de.bwulfert.engine.tests;


import de.bwulfert.engine.controller.CardChooser;
import de.bwulfert.engine.controller.TerminalCardChooser;
import de.bwulfert.engine.modell.Card;
import de.bwulfert.engine.persistence.LocalCardParser;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardChooserTest {

    public static void main(String[] args) {
        LocalCardParser localCardParser = new LocalCardParser();
        List<Card> cards = localCardParser.parseCards(CardChooserTest.class.getClassLoader().getResource("testCards.json").getFile());

        CardChooser cardChooser = new TerminalCardChooser();
        cardChooser.setAvailableCards(cards);
        cardChooser.chooseCards();

        assertEquals(true, cards != null);
    }

}
