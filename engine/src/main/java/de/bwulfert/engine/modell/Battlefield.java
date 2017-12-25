package de.bwulfert.engine.modell;

public class Battlefield {

    private Card[][] battleField;
    private boolean allCardsSet;

    public Battlefield() {
        battleField = new Card[3][3];
    }

    public boolean areAllSlotsSet() {
        return allCardsSet;
    }

    public Card[][] getBattleField() {
        return battleField;
    }

    public void setAllSlotsSet(boolean allSlotsSet) {
        this.allCardsSet = allSlotsSet;
    }

    public void setCardAtPosition(Player player, Card card, int xPos, int yPos) {
        // TODO: use the player as owner of this card
        battleField[yPos][xPos] = card;
    }

}