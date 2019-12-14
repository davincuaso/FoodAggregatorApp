package com.example.finalproject;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;

import com.example.finalproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_maps)
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationReader.LocationReaderCallback {

    private GoogleMap mMap;

    @Bean
    LocationReader locationReader;

    @Click(R.id.button)
    public void locateMe()
    {
        System.out.println("REQUEST LOCATION");
        locationReader.locateMe();
    }

    @AfterViews
    public void init()
    {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationReader.setLocationReaderCallback(this);
    }

    public void newLocation(Location loc)
    {
        // do stuff with the location
        LatLng newLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 1.0f));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng gonzaga = new LatLng(14.639023,121.078017);


        mMap.addMarker(new MarkerOptions().position(gonzaga).title("This is where the stalls are located."));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gonzaga, 16.99f));
    }
}