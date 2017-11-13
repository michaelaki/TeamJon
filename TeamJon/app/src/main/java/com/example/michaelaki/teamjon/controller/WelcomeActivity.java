package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.michaelaki.teamjon.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * File made by michaelaki on 9/14/17.
 */

public class WelcomeActivity extends Activity {
//    private final Map<Integer, String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference();
//        Query query = reference.limitToLast(3200);
//
//        list = new HashMap();
//        query.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                    if (!dataSnapshot.getKey().equals("num_results") && !dataSnapshot.getKey().equals("users")) {
//                        list.put(Integer.parseInt(dataSnapshot.getKey()), dataSnapshot.child("Created Date").getValue().toString());
//                    } else if (dataSnapshot.getKey().equals("users")) {
//                        changeDate();
//                        break;
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
//            }
//        });


        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginScreen();
            }
        });
        Button mRegisterButton = (Button) findViewById(R.id.registerButton);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen();
            }
        });
    }

//    /**
//     * Convert the date from each saved rat sighting to a SimpleDateFormat for filtering
//     */
//    public void changeDate() {
//        Set listNums = list.keySet();
//        System.out.println(list.size());
//        for (Object num: listNums) {
//            int number = Integer.parseInt(num.toString());
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//            DatabaseReference child = reference.child(Integer.toString(number));
//            child.child("Compare Date").push();
//            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//            Date date = new Date();
//            try {
//                int index = list.get(number).indexOf(' ');
//                date = format.parse(list.get(number).substring(0,index));
//            } catch (ParseException e) {
//                Log.e("Whoops", "There's a parse exception");
//            }
//            if (date != new Date()) {
//
//                child.child("Compare Date").setValue(date.getTime());
//            }
//
//
//        }
//        System.out.println("WOW");
//    }

    /**
     * Go to the login screen
     */
    private void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Go to register new user screen
     */
    private void goToRegisterScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
