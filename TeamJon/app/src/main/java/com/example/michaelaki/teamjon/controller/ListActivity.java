package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * File made by michaelaki on 10/10/17.
 */

public class ListActivity extends Activity {

    private ArrayList<RatSighting> rats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        rats = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query reference = database.getReference().limitToLast(50);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RatSighting rat = new RatSighting();
                    if (dataSnapshot.child("Borough").getValue() != null) {
                        rat.setBorough((String) dataSnapshot.child("Borough").getValue());
                    }
                    if (dataSnapshot.child("City").getValue() != null) {
                        rat.setCity((String) dataSnapshot.child("City").getValue());
                    }
                    if (dataSnapshot.child("Created Date").getValue() != null) {
                        rat.setDate((String) dataSnapshot.child("Created Date").getValue());
                    }
                    if (dataSnapshot.child("Incident Address").getValue() != null) {
                        rat.setIncidentAddress((String) dataSnapshot.child("Incident Address").getValue());
                    }
                    if (dataSnapshot.child("Incident Zip").getValue() != null) {
                        rat.setIncidentZip((String) dataSnapshot.child("Incident Zip").getValue());
                    }
                    if (dataSnapshot.child("Latitude").getValue() != null) {
                        rat.setLatitude(Double.parseDouble((String) dataSnapshot.child("Latitude").getValue()));
                    }
                    if (dataSnapshot.child("Location Type").getValue() != null) {
                        rat.setLocationType((String) dataSnapshot.child("Location Type").getValue());
                    }
                    if (dataSnapshot.child("Longitude").getValue() != null) {
                        rat.setLongitude(Double.parseDouble((String) dataSnapshot.child("Longitude").getValue()));
                    }
                    if (dataSnapshot.child("Unique Key").getValue() != null) {
                        rat.setKey((String) dataSnapshot.child("Unique Key").getValue());
                    }
                    rats.add(rat);
                }


                ArrayAdapter<RatSighting> ratAdapter = getAdapter();

                ListView lists = (ListView) findViewById(R.id.ratList);
                lists.setAdapter(ratAdapter);
                lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        goToRatInfo(position);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
            }
        });
    }

    /**
     * Starts the ratInfo activity with the rat at the given position in the list
     * @param position the index in the list of the rat to load more information on
     */
    private void goToRatInfo(int position) {
        Intent intent = new Intent(this, RatInfoActivity.class);
        intent.putExtra("Rat", rats.get(position));
        startActivity(intent);
    }
    /**
     * Go back to the LaunchActivity screen
     */
    private void goBack() {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    /**
     * Creates and returns a new rat sighting adapter
     * @return ArrayAdapter for all the rat sightings
     */
    private ArrayAdapter<RatSighting> getAdapter() {
        return new ArrayAdapter<>(this, R.layout.list_item, rats);
    }
}
