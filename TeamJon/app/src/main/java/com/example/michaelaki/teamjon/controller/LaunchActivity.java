package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michaelaki.teamjon.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * File made by michaelaki on 9/15/17.
 */

public class LaunchActivity extends Activity {
    private int id;
    static String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        admin =  intent.getStringExtra("Admin");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        reference.child("num_results").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                //noinspection ConstantConditions
                setId(Integer.parseInt((Long.toString((Long)dataSnapshot.getValue()))));
                System.out.println(id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddRat();
            }
        });

        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });

        Button dataButton = (Button) findViewById(R.id.dataButton);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDataScreen();
            }
        });

        Button graphButton = (Button) findViewById(R.id.graphButton);
        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGraph();
            }
        });

        Button usersButton = (Button) findViewById(R.id.usersButton);
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewUsers();
            }
        });

        if (admin.equals("true")) {
            usersButton.setVisibility(View.VISIBLE);
        } else {
            usersButton.setVisibility(View.GONE);
        }

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + name + "!");
    }
    /**
     * Go back to the screen to add a new sighting
     */
    private void goToAddRat() {


        Intent intent = new Intent(this, AddRatActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
    /**
     * Logs the user out and go back to the welcome screen
     */
    private void logOut() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
    /**
     * Go to the data screen
     */
    private void goToDataScreen() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    /**
     * Set the id for the next rat to be added the the database
     * @param num the number that the id will be set to
     */
    private void setId(int num) {

        id = num;
    }

    /**
     * Go to the map screen
     */
    private void goToMap() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);

    }

    /**
     * Go to graph screen
     */
    private void goToGraph() {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    /**
     * Go to view users screen
     */
    private void viewUsers() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
