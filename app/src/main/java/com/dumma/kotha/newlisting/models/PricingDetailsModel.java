package com.dumma.kotha.newlisting.models;

/**
 * Created by sregmi on 6/1/18.
 */

public class PricingDetailsModel {

    private String description;
    private long dateAvailable;
    private double rentAmount;

    public PricingDetailsModel() {}


    public PricingDetailsModel(String description, long dateAvailable, double rentAmount) {
        this.description = description;
        this.dateAvailable = dateAvailable;
        this.rentAmount = rentAmount;
    }

    public String getDescription() {
        return description;
    }

    public long getDateAvailable() {
        return dateAvailable;
    }

    public double getRentAmount() {
        return rentAmount;
    }
}
