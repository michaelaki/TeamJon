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
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by quintinroper on 12/4/17.
 */

public class UserActivity extends Activity {
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        users = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query reference = database.getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user;
                    if ((dataSnapshot.child("Admin").getValue()).equals("false")) {
                         user = new User(dataSnapshot.getKey(),
                                 (String) dataSnapshot.child("Password").getValue(),
                                 (String) dataSnapshot.child("Name").getValue());
                    } else {
                         user = new Admin(dataSnapshot.getKey(),
                                 (String) dataSnapshot.child("Password").getValue(),
                                 (String) dataSnapshot.child("Name").getValue());
                    }
                    user.setAttempts((int) (long) dataSnapshot.child("Attempts").getValue());
                    users.add(user);
                }

                ArrayAdapter<User> userAdapter = getAdapter();

                ListView lists = (ListView) findViewById(R.id.usersList);
                lists.setAdapter(userAdapter);
                lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        goToUserInfo(position);
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
    private void goToUserInfo(int position) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra("User", (Serializable) users.get(position));
        startActivity(intent);
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
     * Creates and returns a new rat sighting adapter
     * @return ArrayAdapter for all the rat sightings
     */
    private ArrayAdapter<User> getAdapter() {
        return new ArrayAdapter<>(this, R.layout.list_item, users);
    }
}
