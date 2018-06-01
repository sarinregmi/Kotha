package com.dumma.kotha;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


/**
 * Handles accessing location using google location services
 */
public class LocationManager extends LiveData<Location> implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private final static String TAG = LocationManager.class.getSimpleName();
    public static final int LOCATION_PERMISSIONS_REQUEST = 1;
    private Activity activity;
    private GoogleApiClient googleApiClient;

    public LocationManager(Activity activity) {
        this.activity = activity;
        googleApiClient =
                new GoogleApiClient.Builder(activity, this, this)
                        .addApi(LocationServices.API)
                        .build();
    }

    @Override
    protected void onActive() {
        googleApiClient.connect();
    }

    @Override
    protected void onInactive() {
        googleApiClient.disconnect();
    }

    public boolean requestLocationPermissionsIfNecessary() {
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSIONS_REQUEST);
            return false;
        } else {
            return true;
        }
    }

    private void requestLastLocation() {
        if (requestLocationPermissionsIfNecessary()) {
            try {
                LocationServices.getFusedLocationProviderClient(activity).getLastLocation()
                        .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location loc) {
                                if (loc != null) {
                                    Log.v(TAG, "Oooh got the location");
                                    setValue(loc);
                                } else {
                                    Log.v(TAG, "Did not get location");
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error trying to get last GPS location");
                    }
                });
            } catch (SecurityException e)  {
                Log.e(TAG, "Exception: " +  e.getMessage());
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        setValue(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLastLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        // TODO
   }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO
    }
}
