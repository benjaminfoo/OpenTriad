package de.bwulfert.engine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Card implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("topPower")
    @Expose
    private int topPower;

    @SerializedName("rightPower")
    @Expose
    private int rightPower;

    @SerializedName("bottomPower")
    @Expose
    private int bottomPower;

    @SerializedName("leftPower")
    @Expose
    private int leftPower;

    private final static long serialVersionUID = -8502853696179136521L;

    /**
     * No args constructor for use in serialization
     */
    public Card() {
    }

    public Card(String name, int topPower, int rightPower, int bottomPower, int leftPower) {
        this.name = name;
        this.topPower = topPower;
        this.rightPower = rightPower;
        this.bottomPower = bottomPower;
        this.leftPower = leftPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopPower() {
        return topPower;
    }

    public void setTopPower(int topPower) {
        this.topPower = topPower;
    }

    public int getRightPower() {
        return rightPower;
    }

    public void setRightPower(int rightPower) {
        this.rightPower = rightPower;
    }

    public int getBottomPower() {
        return bottomPower;
    }

    public void setBottomPower(int bottomPower) {
        this.bottomPower = bottomPower;
    }

    public int getLeftPower() {
        return leftPower;
    }

    public void setLeftPower(int leftPower) {
        this.leftPower = leftPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (topPower != card.topPower) return false;
        if (rightPower != card.rightPower) return false;
        if (bottomPower != card.bottomPower) return false;
        if (leftPower != card.leftPower) return false;
        return name != null ? name.equals(card.name) : card.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + topPower;
        result = 31 * result + rightPower;
        result = 31 * result + bottomPower;
        result = 31 * result + leftPower;
        return result;
    }

    @Override
    public String toString() {
        return name + " (" +
                topPower + "," +
                rightPower + "," +
                bottomPower + "," +
                leftPower +
                ")";
    }

}