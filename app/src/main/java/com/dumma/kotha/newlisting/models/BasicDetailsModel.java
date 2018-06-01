package com.dumma.kotha.newlisting.models;

/**
 * Created by sregmi on 5/30/18.
 */

public class BasicDetailsModel {

    private Listing.TYPE type;
    private int numberOfRooms;
    private int numberOfBath;
    private int floorNumber;

    public BasicDetailsModel() {}


    public BasicDetailsModel(Listing.TYPE type, int numberOfRooms, int numberOfBath, int floorNumber) {
        this.type = type;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBath = numberOfBath;
        this.floorNumber = floorNumber;
    }

    public Listing.TYPE getType() {
        return type;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getNumberOfBath() {
        return numberOfBath;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
