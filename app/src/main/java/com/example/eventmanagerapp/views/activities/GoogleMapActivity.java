package com.example.eventmanagerapp.views.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import com.example.eventmanagerapp.R;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
//import android.support.v4.app.FragmentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Geocoder geocoder;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;

    private String locationToFocus;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        geocoder = new Geocoder(this, Locale.getDefault());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationToFocus = getIntent().getExtras().getString("location","Australia");
        categoryName = getIntent().getExtras().getString("name", "Unknown");

        Toolbar toolbar = findViewById(R.id.map_toolbar);
        toolbar.setTitle("View Map");

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        findLocationMoveCamera();
    }

    private void findLocationMoveCamera() {
        // initialise Geocode to search location using String
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        // getFromLocationName method works for API 33 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            /**
             * countryToFocus: String value, any string we want to search
             * maxResults: how many results to return if search was successful
             * successCallback method: if results are found, this method will be executed
             *                          runs in a background thread
             */
            geocoder.getFromLocationName(locationToFocus, 1, addresses -> {
                // if there are results, this condition would return true
                if (!addresses.isEmpty()) {
                    // run on UI thread as the user interface will update once set map location
                    runOnUiThread(() -> {
                        // define new LatLng variable using the first address from list of addresses
                        LatLng newAddressLocation = new LatLng(
                                addresses.get(0).getLatitude(),
                                addresses.get(0).getLongitude()
                        );

                        // repositions the camera according to newAddressLocation
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(newAddressLocation));

                        // just for reference add a new Marker
                        mMap.addMarker(
                                new MarkerOptions()
                                        .position(newAddressLocation)
                                        .title(locationToFocus)
                        );
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                        mMap.addMarker(new MarkerOptions().position(newAddressLocation).title(categoryName));
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Category address not found", Toast.LENGTH_LONG).show();
                    });
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        startDashboardButton();
        return true;
    }

    public void startDashboardButton(){
        // switch to activity that shows fruit details
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

}