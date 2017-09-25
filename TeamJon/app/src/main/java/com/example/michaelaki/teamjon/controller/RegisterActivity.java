package com.example.michaelaki.teamjon.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.michaelaki.teamjon.R;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.Model;
import com.example.michaelaki.teamjon.model.User;

/**
 * Created by michaelaki on 9/13/17.
 */

public class RegisterActivity extends Activity {
    private EditText emailField;
    private EditText passwordField;

    private User user;
    private boolean admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailField = (EditText) findViewById(R.id.email_text);
        passwordField = (EditText) findViewById(R.id.password_text);
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.registrationButton || id == EditorInfo.IME_NULL) {
                    register();
                    return true;
                }
                return false;
            }
        });
        Button mRegisterButton = (Button) findViewById(R.id.registrationButton);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

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

    public void register() {
        Model model = Model.getInstance();
        if (admin) {
            user = new Admin(emailField.getText().toString(), passwordField.getText().toString());
        } else {
            user = new User(emailField.getText().toString(), passwordField.getText().toString());
        }
        model.addUser(user);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
