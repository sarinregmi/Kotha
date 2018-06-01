package com.dumma.kotha.newlisting;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dumma.kotha.LocationManager;
import com.dumma.kotha.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class ChooseLocationFragment extends AddListingFragment implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    public static String TAG = ChooseLocationFragment.class.getSimpleName();
    private LocationSetListener locationSetListener;
    private GoogleMap map;
    private Location location;
    private LocationManager locationManager;

    public ChooseLocationFragment() {
    }

    public static ChooseLocationFragment newInstance() {
        return new ChooseLocationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_location, container, false);
        v.findViewById(R.id.submit).setOnClickListener(view -> {
            if (locationSetListener != null) {
                locationSetListener.submitLocation(location);
            }
        });
        locationManager = new LocationManager(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LocationSetListener) {
            locationSetListener = (LocationSetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement locationSetListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        locationSetListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        updateLocationUI();
        fetchCurrentLocation();
    }

    public interface LocationSetListener {
        void submitLocation(Location location);
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.getUiSettings().setCompassEnabled(true);

        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void fetchCurrentLocation() {
        if (locationManager.requestLocationPermissionsIfNecessary()) {
            locationManager.observe(this, loc -> {
                if (loc != null) {
                    location = loc;
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14.0f));
                }
            });
        }
    }

    @Override
    public void onCameraIdle() {
        LatLng latLng = map.getCameraPosition().target;
        if (latLng != null) {
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);
        }
    }
}
