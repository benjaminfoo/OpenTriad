package org.owls.opentriad.modell;

/**
 * Created by owl on 12/21/17.
 * <p>
 * Generated with: http://www.jsonschema2pojo.org/
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class JsonCard implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("north")
    @Expose
    private String north;
    @SerializedName("west")
    @Expose
    private String west;
    @SerializedName("east")
    @Expose
    private String east;
    @SerializedName("south")
    @Expose
    private String south;
    private final static long serialVersionUID = -8502853696179136521L;

    /**
     * No args constructor for use in serialization
     */
    public JsonCard() {
    }

    /**
     * @param name
     * @param south
     * @param east
     * @param north
     * @param west
     */
    public JsonCard(String name, String north, String west, String east, String south) {
        super();
        this.name = name;
        this.north = north;
        this.west = west;
        this.east = east;
        this.south = south;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonCard jsonCard = (JsonCard) o;

        if (name != null ? !name.equals(jsonCard.name) : jsonCard.name != null) return false;
        if (north != null ? !north.equals(jsonCard.north) : jsonCard.north != null) return false;
        if (west != null ? !west.equals(jsonCard.west) : jsonCard.west != null) return false;
        if (east != null ? !east.equals(jsonCard.east) : jsonCard.east != null) return false;
        return south != null ? south.equals(jsonCard.south) : jsonCard.south == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (north != null ? north.hashCode() : 0);
        result = 31 * result + (west != null ? west.hashCode() : 0);
        result = 31 * result + (east != null ? east.hashCode() : 0);
        result = 31 * result + (south != null ? south.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card {" +
                "name='" + name + '\'' +
                ", north='" + north + '\'' +
                ", west='" + west + '\'' +
                ", east='" + east + '\'' +
                ", south='" + south + '\'' +
                '}';
    }

    public String getPowers() {
        return north + ", " + east + ", " + south + ", " + west;
    }

    public int[] getPowersArray() {
        int north = Integer.valueOf(getNorth());
        int east = Integer.valueOf(getEast());
        int south = Integer.valueOf(getSouth());
        int west = Integer.valueOf(getWest());
        return new int[]{north, east, south, west};
    }

    public int getPowerSum() {
        int north = Integer.valueOf(getNorth());
        int east = Integer.valueOf(getEast());
        int south = Integer.valueOf(getSouth());
        int west = Integer.valueOf(getWest());
        return north + east + south + west;
    }

    public int getPowerByDirection(Direction direction) {
        int[] powers = {
                Integer.valueOf(getNorth()),
                Integer.valueOf(getEast()),
                Integer.valueOf(getSouth()),
                Integer.valueOf(getWest())
        };

        return powers[direction.ordinal()];
    }
}