package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.Model;
import com.example.michaelaki.teamjon.model.User;

/**
 * Created by quintinroper on 10/16/17.
 */

public class AddRatActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Name");

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logOut();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + user + "!");
    }
    /**
     * Go back to the ListActivity screen
     */
    public void goBack() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    /**
     * Add a rat sighting instace to the map
     */
    public void add() {
        /*Model model = Model.getInstance();
        if (admin) {
            user = new Admin(emailField.getText().toString(), passwordField.getText().toString(), nameField.getText().toString());
        } else {
            user = new User(emailField.getText().toString(), passwordField.getText().toString(), nameField.getText().toString());
        }
        model.addUser(user);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);*/
    }
}
