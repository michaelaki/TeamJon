package com.example.michaelaki.teamjon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by michaelaki on 9/15/17.
 */

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Username");

        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome " + user + "!");
    }

    public void logOut() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
