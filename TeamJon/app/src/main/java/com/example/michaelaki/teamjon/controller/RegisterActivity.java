package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * File made by michaelaki on 9/13/17.
 */

public class RegisterActivity extends Activity {
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;


    private boolean admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailField = (EditText) findViewById(R.id.email_text);
        passwordField = (EditText) findViewById(R.id.password_text);
        nameField = (EditText) findViewById(R.id.name_text);
        /*passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.registrationButton || id == EditorInfo.IME_NULL) {
                    register();
                    return true;
                }
                return false;
            }
        });*/
        Button mRegisterButton = (Button) findViewById(R.id.registrationButton);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelRegistrationButton);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToWelcomeScreen();
            }
        });
    }

    /**
     * Go to welcome screen
     */
    private void returnToWelcomeScreen() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Check which user option is selected (admin or user)
     * @param view Current view with the radio buttons
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioAdmin:
                if (checked)
                    admin = true;
                    break;
            case R.id.radioUser:
                if (checked)
                    admin = false;
                    break;
        }
    }

    /**
     * Get which type of user the user is (admin or normal user)
     */
    public User getUserType(boolean isAdmin) {
        if (isAdmin) {
            return new Admin(emailField.getText().toString(), passwordField.getText().toString(), nameField.getText().toString());
        } else {
            return new User(emailField.getText().toString(), passwordField.getText().toString(), nameField.getText().toString());
        }
    }

    /**
     * Add new user to database
     */
    private void register() {
        User user;
        user = getUserType(admin);
        boolean validPassword = user.validatePassword(user.getPassword());
        boolean validEmail = user.validateEmail(user.getEmail());
        if (validPassword && validEmail) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference().child("users");
            reference.child(user.getEmail()).push();
            reference.child(user.getEmail()).child("Name").push();
            reference.child(user.getEmail()).child("Name").setValue(user.getName());
            reference.child(user.getEmail()).child("Password").push();
            reference.child(user.getEmail()).child("Password").setValue(user.getPassword());
            reference.child(user.getEmail()).child("Attempts").push();
            reference.child(user.getEmail()).child("Attempts").setValue(0);

            returnToWelcomeScreen();
        } else if (!validPassword){
            Toast.makeText(this, "Password must be greater than 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Must enter a valid email address", Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * Print a message to the screen telling the user that they input invalid information
//     */
//    private void makeToast() {
//        Toast.makeText(this, "Username is already in use. Please try a different Username",
//                Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * Checks the database to confirm if the user already exists
//     */
//    private void validateUsername() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        Query reference = database.getReference().child("users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("#################################");
//                boolean found = false;
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    if (dataSnapshot.getKey().equals(emailField.getText().toString())) {
//                        found = true;
//                    }
//                }
//                if (!found) {
//                    register();
//                } else {
//                    makeToast();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
//            }
//        });
//    }
}
