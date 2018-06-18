package com.dumma.kotha.newlisting.models;

import android.location.Location;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Date;


/**
 * Created by sregmi on 5/25/18.
 */

@IgnoreExtraProperties
public class Listing  {

    private String id;
    private User author;
    private List<String> images;
    private int views;
    private double latitude;
    private double longitude;
    private @ServerTimestamp Date postingDate;


    private BasicDetailsModel basicDetailsModel;
    private PricingDetailsModel pricingDetailsModel;


    public Listing() {}

    public void setBasicDetails(BasicDetailsModel bdm) {
        this.basicDetailsModel = bdm;
    }

    public void setPricingDetails(PricingDetailsModel pdm) {
        this.pricingDetailsModel = pdm;
    }

    public void setLocation(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public String getTitle() {
        return null;
    }

    public User getAuthor() {
        return author;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("NPR"));
        String result = format.format(pricingDetailsModel.getRentAmount());
        return result;
    }

    public Date getPostingDate() {
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
