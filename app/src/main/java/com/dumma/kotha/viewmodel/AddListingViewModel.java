package com.dumma.kotha.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;

import com.dumma.kotha.newlisting.models.BasicDetailsModel;
import com.dumma.kotha.newlisting.models.Listing;
import com.dumma.kotha.newlisting.models.PricingDetailsModel;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by sregmi on 6/1/18.
 */

public class AddListingViewModel extends ViewModel {

    private FirebaseFirestore db;
    private final MutableLiveData<Boolean> isSuccessful;
    private Listing listing;

    public AddListingViewModel() {
        db = FirebaseFirestore.getInstance();
        isSuccessful = new MutableLiveData<>();
        listing = new Listing();
    }

    public LiveData<Boolean> addListing() {
        db.collection("listings")
                .add(listing)
                .addOnSuccessListener(documentReference -> {

                    isSuccessful.setValue(true);
                }).addOnFailureListener(e -> {
                    isSuccessful.setValue(false);
                });
        return isSuccessful;
    }

    public void setLocation(Location location) {
        listing.setLocation(location);
    }

    public void setBasicDetails(BasicDetailsModel model) {
        listing.setBasicDetails(model);
    }

    public void setPricingDetails(PricingDetailsModel model) {
        listing.setPricingDetails(model);
    }

    public Listing getListing() {
        return listing;
    }
}
