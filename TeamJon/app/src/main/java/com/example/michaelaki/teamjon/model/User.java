package com.example.michaelaki.teamjon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by quintinroper on 9/18/17.
 */

public class User implements Parcelable {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String newEmail) { email = newEmail; }

    public String getPassword() { return password; }
    public void setPassword(String newPassword) { password = newPassword; }

    /**
     * 2-arg constructor that creates a User with an email and password
     * @param newEmail User's email
     * @param newPassword User's password
     */
    public User(String newEmail, String newPassword) {
        email = newEmail;
        password = newPassword;
    }

    /**
     * toString method used to visualize the User's email and password
     * @return a String containing the User's email and password
     */
    @Override
    public String toString() { return email + " | " + password; }


    /* ***************************************************** */
    private User(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
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
