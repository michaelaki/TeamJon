package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private String foundPassword;
    private String foundName;
    private boolean found;
    private String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToWelcomeScreen();
            }
        });
    }
    /**
     * Go back to the Welcome screen
     */
    private void returnToWelcomeScreen() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Advance to the Launch screen
     */
    private void login() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.putExtra("Admin", admin);
        intent.putExtra("Name", foundName);
        startActivity(intent);
    }

    /**
     * Print a message to the screen telling the user that they input invalid information
     */
    private void dneToast() {
        Toast.makeText(this, "Invalid Email and/or Password", Toast.LENGTH_SHORT).show();
    }

    /**
     * Print a message to the screen telling the user that their account is locked
     */
    private void lockedToast() {
        Toast.makeText(this, "This account is locked, please contact an admin", Toast.LENGTH_SHORT).show();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query reference = database.getReference().child("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals(mEmailView.getText().toString())) {
                        foundPassword = (String) dataSnapshot.child("Password").getValue();
                        foundName = (String) dataSnapshot.child("Name").getValue();
                        found = true;
                        if (foundPassword.equals(mPasswordView.getText().toString())) {
                            if ((int) (long) dataSnapshot.child("Attempts").getValue() < 5) {
                                admin = (String) dataSnapshot.child("Admin").getValue();
                                login();
                            } else {
                                lockedToast();
                            }
                        } else {
                            database.getReference().child("users").child(mEmailView.getText().toString()).child("Attempts").setValue((int) (long) dataSnapshot.child("Attempts").getValue() + 1);
                            found = false;
                        }
                    }
                }
                if (!found) {
                    dneToast();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Failed to read value", databaseError.toString(), databaseError.toException());
            }
        });
    }
}

