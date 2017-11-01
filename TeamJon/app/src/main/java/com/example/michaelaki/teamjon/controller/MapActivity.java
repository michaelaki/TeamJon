package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Filter;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelaki on 10/25/17.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    private Filter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        filter = new Filter();
        Intent intent = getIntent();
        if (intent.getSerializableExtra("Filter") != null) {
            filter = (Filter) intent.getSerializableExtra("Filter");
        }

        Button backButton = (Button) findViewById(R.id.returnToLaunch);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, LaunchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        Button filterButton = (Button) findViewById((R.id.filterButton));
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, FilterActivity.class);
                intent.putExtra("Filter", filter);
                startActivity(intent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng newYork = new LatLng(40.7128, -74.0060);
        mMap.addMarker(new MarkerOptions().position(newYork).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 15));
        addMarkers();
    }

    public void addMarkers() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (filter != null) {

            Query markerQuery = firebaseDatabase.getReference().startAt(Integer.toString(filter.getStartDate()), "Compare Date")
                    .endAt(Integer.toString(filter.getEndDate()), "Compare Date");
            markerQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    List<RatSighting> rats = new ArrayList();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        RatSighting rat = new RatSighting();
                        if (dataSnapshot.child("Borough").getValue() != null) {
                            rat.setBorough(dataSnapshot.child("Borough").getValue().toString());
                        }
                        if (dataSnapshot.child("City").getValue() != null) {
                            rat.setCity(dataSnapshot.child("City").getValue().toString());
                        }
                        if (dataSnapshot.child("Created Date").getValue() != null) {
                            rat.setDate(dataSnapshot.child("Created Date").getValue().toString());
                        }
                        if (dataSnapshot.child("Incident Address").getValue() != null) {
                            rat.setIncidentAddress(dataSnapshot.child("Incident Address").getValue().toString());
                        }
                        if (dataSnapshot.child("Incident Zip").getValue() != null) {
                            rat.setIncidentZip(dataSnapshot.child("Incident Zip").getValue().toString());
                        }
                        if (dataSnapshot.child("Latitude").getValue() != null) {
                            rat.setLatitude(Double.parseDouble(dataSnapshot.child("Latitude").getValue().toString()));
                        }
                        if (dataSnapshot.child("Location Type").getValue() != null) {
                            rat.setLocationType(dataSnapshot.child("Location Type").getValue().toString());
                        }
                        if (dataSnapshot.child("Longitude").getValue() != null) {
                            rat.setLongitude(Double.parseDouble(dataSnapshot.child("Longitude").getValue().toString()));
                        }
                        if (dataSnapshot.child("Unique Key").getValue() != null) {
                            rat.setKey(dataSnapshot.child("Unique Key").getValue().toString());
                        }
                        rats.add(rat);

                    }
                    for (int k = 0; k < rats.size(); k++) {
                        LatLng latLng = new LatLng(rats.get(k).getLatitude(), rats.get(k).getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(rats.get(k).getKey()).snippet(rats.get(k).getDate()));
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
                }
            });
        } else {
            Query markerQuery = firebaseDatabase.getReference().limitToLast(3000);
            markerQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    List<RatSighting> rats = new ArrayList();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        RatSighting rat = new RatSighting();
                        if (dataSnapshot.child("Borough").getValue() != null) {
                            rat.setBorough(dataSnapshot.child("Borough").getValue().toString());
                        }
                        if (dataSnapshot.child("City").getValue() != null) {
                            rat.setCity(dataSnapshot.child("City").getValue().toString());
                        }
                        if (dataSnapshot.child("Created Date").getValue() != null) {
                            rat.setDate(dataSnapshot.child("Created Date").getValue().toString());
                        }
                        if (dataSnapshot.child("Incident Address").getValue() != null) {
                            rat.setIncidentAddress(dataSnapshot.child("Incident Address").getValue().toString());
                        }
                        if (dataSnapshot.child("Incident Zip").getValue() != null) {
                            rat.setIncidentZip(dataSnapshot.child("Incident Zip").getValue().toString());
                        }
                        if (dataSnapshot.child("Latitude").getValue() != null) {
                            rat.setLatitude(Double.parseDouble(dataSnapshot.child("Latitude").getValue().toString()));
                        }
                        if (dataSnapshot.child("Location Type").getValue() != null) {
                            rat.setLocationType(dataSnapshot.child("Location Type").getValue().toString());
                        }
                        if (dataSnapshot.child("Longitude").getValue() != null) {
                            rat.setLongitude(Double.parseDouble(dataSnapshot.child("Longitude").getValue().toString()));
                        }
                        if (dataSnapshot.child("Unique Key").getValue() != null) {
                            rat.setKey(dataSnapshot.child("Unique Key").getValue().toString());
                        }
                        rats.add(rat);

                    }
                    for (int k = 0; k < rats.size() - 2; k++) {
                        LatLng latLng = new LatLng(rats.get(k).getLatitude(), rats.get(k).getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(rats.get(k).getKey()).snippet(rats.get(k).getDate()));
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
                }
            });
        }
    }
}