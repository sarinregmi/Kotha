package com.dumma.kotha.newlisting.models;

/**
 * Created by sregmi on 6/1/18.
 */

public enum TYPE {

    FLAT ("Flat"),
    ROOMS("Rooms"),
    HOUSE("House"),
    SHUTTER("Shutter"),
    SHUTTER_WITH_ROOMS("Shutter with rooms");

    private String value;

    TYPE(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
