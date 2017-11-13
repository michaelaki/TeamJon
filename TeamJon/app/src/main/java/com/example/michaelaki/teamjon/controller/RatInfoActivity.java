package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.RatSighting;

import java.util.List;

/**
 * File made by michaelaki on 10/11/17.
 */

public class RatInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_info);
        Intent intent = getIntent();
        RatSighting rat = (RatSighting) intent.getSerializableExtra("Rat");

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        TextView key = (TextView) findViewById(R.id.key);
        key.setText(rat.getKey());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(rat.getDate());

        TextView locationType = (TextView) findViewById(R.id.locationType);
        locationType.setText(rat.getLocationType());

        TextView zip = (TextView) findViewById(R.id.zip);
        zip.setText(rat.getIncidentZip());

        TextView address = (TextView) findViewById(R.id.address);
        address.setText(rat.getIncidentAddress());

        TextView city = (TextView) findViewById(R.id.city);
        city.setText(rat.getCity());

        TextView borough = (TextView) findViewById(R.id.borough);
        borough.setText(rat.getBorough());

        TextView latLong = (TextView) findViewById(R.id.latLong);
        latLong.setText(rat.getLatitude() + ", " + rat.getLongitude());
    }

    /**
     * Go back to the ListActivity screen
     */
    public void goBack() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
