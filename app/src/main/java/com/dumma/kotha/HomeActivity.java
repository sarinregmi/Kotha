package com.dumma.kotha;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dumma.kotha.newlisting.AddListingActivity;
import com.dumma.kotha.newlisting.models.Listing;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
         ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private GoogleMap map;
    private List<Listing> listings;
    private LocationManager locationManager;
    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();

        listings = new ArrayList<>();

        View bottomSheet = findViewById( R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setSkipCollapsed(true);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void initUI() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
//                startActivity(AddNoteActivity.getIntent(this));
//                bottomSheetBehavior.setPeekHeight(400);
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                startActivity(AddListingActivity.getIntent(this));
                break;
            default:
                break;
        }
        return true;
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);
        locationManager = new LocationManager(this);
        if (locationManager.requestLocationPermissionsIfNecessary()) {
            updateLocationUI();
        }
        displayNotesOnMap();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Integer tag = (Integer) marker.getTag();
        if (tag != null) {
            Listing item = listings.get(tag);
            displayItemData(item);
        }
        return false;
    }

    /**
     * Displays the LandMarks on the map
     */
    private void displayNotesOnMap() {
        if (map != null ) {
            map.clear();
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i = 0; i < listings.size(); i++) {
                Listing item = listings.get(i);
                LatLng latLng = new LatLng(item.getLatitude(), item.getLongitude());
                Marker marker = map.addMarker(new MarkerOptions().position(latLng).title(item.getPrice()));
                marker.setTag(i);
                marker.showInfoWindow();
                builder.include(marker.getPosition());
            }
            if (listings.size() > 0) {
                LatLngBounds bounds = builder.build();
                int padding = 20;
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                map.moveCamera(cu);
                map.animateCamera(cu);
                displayItemData(listings.get(0));
            }
        }
    }

    /**
     * Displays content of the note item
     * @param @NoteItem
     */
    private void displayItemData(Listing item) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationManager.LOCATION_PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocationUI();
                } else {
                    Toast.makeText(this, "Cannot show your position without location permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
