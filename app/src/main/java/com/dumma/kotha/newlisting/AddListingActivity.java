package com.dumma.kotha.newlisting;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dumma.kotha.R;
import com.dumma.kotha.newlisting.models.BasicDetailsModel;

public class AddListingActivity extends AppCompatActivity implements ChooseLocationFragment.LocationSetListener, BasicDetailsFragment.BasicDetailsSetListener {

    private Location location;
    private BasicDetailsModel bdm;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddListingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_clear_24);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, ChooseLocationFragment.newInstance(), ChooseLocationFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    public void submitLocation(Location location) {
        this.location = location;
        replaceFragment(BasicDetailsFragment.newInstance(), BasicDetailsFragment.TAG);
    }

    private void replaceFragment(Fragment f, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, f,tag)
                .addToBackStack(tag)
                .commit();

    }

    @Override
    public void submitBasicDetails(BasicDetailsModel model) {

    }
}
