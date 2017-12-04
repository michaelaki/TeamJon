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
import android.widget.TextView;
import android.widget.Toast;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.RatSighting;
import com.example.michaelaki.teamjon.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by quintinroper on 12/4/17.
 */

public class UserInfoActivity extends Activity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");

        TextView admin = (TextView) findViewById(R.id.admin);
        if (user instanceof Admin) {
            admin.setVisibility(View.VISIBLE);
            admin.setText("ADMIN");
        } else {
            admin.setVisibility(View.GONE);
        }

        TextView email = (TextView) findViewById(R.id.email);
        email.setText("Email: "+user.getEmail());

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Name: "+user.getName());

        TextView password = (TextView) findViewById(R.id.password);
        password.setText("Password: "+user.getPassword());

        TextView attempts = (TextView) findViewById(R.id.attempts);
        attempts.setText("Attempts: "+user.getAttempts());

        Button unlockButton = (Button) findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlock();
                goBack();
            }
        });

        if (user.getAttempts() >= 5) {
            unlockButton.setVisibility(View.VISIBLE);
        } else {
            unlockButton.setVisibility(View.GONE);
        }

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void unlock() {
        user.setAttempts(0);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("users").child(user.getEmail());
        reference.child("Attempts").setValue(0);
        Toast.makeText(this, "Account Successfully Unlocked", Toast.LENGTH_SHORT).show();
    }

    /**
     * Go back to the ListActivity screen
     */
    private void goBack() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
