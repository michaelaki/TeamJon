package com.example.michaelaki.teamjon.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * File made by quintinroper on 9/18/17.
 */

public class User implements Parcelable, Serializable {
    private final String email;
    private final String password;
    private final String name;
    private int attempts;

    /**
     * Getter for email
     * @return User's email
     */
    public String getEmail() { return email; }

    /**
     * Getter for password
     * @return User's password
     */
    public String getPassword() { return password; }

    public int getAttempts() { return attempts; }

    public void setAttempts(int num) { attempts = num; }

    /**
     * Validates that password greater than 6 characters
     * @param newPassword new password for User
     */
    public boolean validatePassword(String newPassword) {
        return newPassword != null && (newPassword.length() > 6);
    }

    public boolean validateEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Getter for name
     * @return User's name
     */
    public String getName() { return name; }

    /**
     * 2-arg constructor that creates a User with an email and password
     * @param newEmail User's email
     * @param newPassword User's password
     * @param newName User's name
     */
    public User(String newEmail, String newPassword, String newName) {
        email = newEmail;
        password = newPassword;
        name = newName;
        attempts = 0;
    }

    /**
     * toString method used to visualize the User's email and password
     * @return a String containing the User's email and password
     */
    @Override
    public String toString() { return name + ", " + email + ", " + password; }


    /* ***************************************************** */
    private User(Parcel in) {
        email = in.readString();
        password = in.readString();
        name = in.readString();
        attempts = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeInt(attempts);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
