package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by michaelaki on 10/10/17.
 */

public class ListActivity extends Activity {

    private ArrayList<RatSighting> rats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        rats = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

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

                ArrayAdapter<RatSighting> ratAdapter = getAdapter();

                ListView lists = (ListView) findViewById(R.id.ratList);
                lists.setAdapter(ratAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
            }
        });






    }

    public ArrayAdapter getAdapter() {
        return new ArrayAdapter<RatSighting>(this, R.layout.list_item, rats);
    }
}
