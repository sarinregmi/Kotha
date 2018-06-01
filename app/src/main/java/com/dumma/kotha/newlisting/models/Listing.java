package com.dumma.kotha.newlisting.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by sregmi on 5/25/18.
 */

@IgnoreExtraProperties
public class Listing  {

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

    private String title;
    private String author;
    private String description;
    private List<String> images;
    private double price;
    private long availableDate;
    private long postingDate;
    private int views;
    private double latitude;
    private double longitude;
    private int numberOfRooms;
    private int numberOfBathroom;
    private boolean isSharedBath;

    public Listing() {}


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("NPR"));
        String result = format.format(price);
        return result;
    }

    public long getAvailableDate() {
        return availableDate;
    }

    public long getPostingDate() {
        return postingDate;
    }

    public int getViews() {
        return views;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
