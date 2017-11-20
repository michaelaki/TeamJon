package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.example.michaelaki.teamjon.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File made by quintinroper on 10/16/17.
 */

public class AddRatActivity extends Activity {

    private int id;
    private RatSighting rat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);
        rat = new RatSighting();
        id = getIntent().getIntExtra("ID", 0);
        rat.setKey(Integer.toString(id));

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });




        Button backButton = (Button) findViewById(R.id.cancelButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    /**
     * Go back to the LaunchActivity screen
     */
    private void goBack() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
    /**
     * Attempts to create a new rat sighting.
     * If there are form errors (invalid #s for lat/long, invalid date/location), the
     * errors are presented and no activity is made
     */
    private void add() {
        boolean notValidNumber = true;
        EditText latitude = (EditText) findViewById(R.id.latitude);
        try {
            rat.setLatitude(Double.parseDouble(latitude.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Must use numbers for latitude and longitude", Toast.LENGTH_SHORT).show();
            notValidNumber = false;
        }
        EditText longitude = (EditText) findViewById(R.id.longitude);
        try {
            rat.setLongitude(Double.parseDouble(longitude.getText().toString()));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Must use numbers for latitude and longitude", Toast.LENGTH_SHORT).show();
            notValidNumber = false;
        }
        if (notValidNumber) {
            EditText borough = (EditText) findViewById(R.id.borough);
            rat.setBorough(borough.getText().toString());
            EditText city = (EditText) findViewById(R.id.city);
            rat.setCity(city.getText().toString());
            EditText locationType = (EditText) findViewById(R.id.locationType);
            rat.setLocationType(locationType.getText().toString());
            DatePicker date = (DatePicker) findViewById(R.id.datePicker);
            TimePicker time = (TimePicker) findViewById(R.id.timePicker);
            rat.setDate(date.getMonth() + "/" + date.getDayOfMonth() + "/" + date.getYear() + " "
                    + time.getHour() + ":" + time.getMinute() + ":00");

            EditText incidentAddress = (EditText) findViewById(R.id.incidentAddress);
            rat.setIncidentAddress(incidentAddress.getText().toString());
            EditText incidentZip = (EditText) findViewById(R.id.incidentZip);
            rat.setIncidentZip(incidentZip.getText().toString());


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference reference = database.getReference();


            reference.child("num_results").setValue(id + 1);


            DatabaseReference keyReference = reference.child(rat.getKey());
            keyReference.child("Borough").push();
            keyReference.child("Borough").setValue(rat.getBorough());
            keyReference.child("City").push();
            keyReference.child("City").setValue(rat.getCity());
            keyReference.child("Compare Date").push();
            String unformattedDate = rat.getDate();


            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date formatDate = new Date();
            try {
                int index = unformattedDate.indexOf(' ');
                formatDate = format.parse(unformattedDate.substring(0,index));
            } catch (ParseException e) {
                Log.e("Whoops", "There's a parse exception");
            }
            keyReference.child("Compare Date").setValue(formatDate.getTime());
            keyReference.child("Created Date").push();
            keyReference.child("Created Date").setValue(rat.getDate());
            keyReference.child("Incident Address").push();
            keyReference.child("Incident Address").setValue(rat.getIncidentAddress());
            keyReference.child("Incident Zip").push();
            keyReference.child("Incident Zip").setValue(rat.getIncidentZip());
            keyReference.child("Latitude").push();
            keyReference.child("Latitude").setValue(rat.getLatitude());
            keyReference.child("Location Type").push();
            keyReference.child("Location Type").setValue(rat.getLocationType());
            keyReference.child("Longitude").push();
            keyReference.child("Longitude").setValue(rat.getLongitude());
            keyReference.child("Unique Key").push();
            keyReference.child("Unique Key").setValue(rat.getKey());
            Intent intent = new Intent(this, LaunchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }


}
