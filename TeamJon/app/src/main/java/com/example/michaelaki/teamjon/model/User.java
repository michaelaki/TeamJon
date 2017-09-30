package com.example.michaelaki.teamjon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by quintinroper on 9/18/17.
 */

public class User implements Parcelable {
    private String username;
    private String password;

    /**
     * Getter for username
     * @return User's username
     */
    public String getUsername() { return username; }

    /**
     * Setter for username
     * @param newUsername new username for User
     */
    public void setEmail(String newUsername) { username = newUsername; }

    /**
     * Getter for password
     * @return User's password
     */
    public String getPassword() { return password; }

    /**
     * Setter for password
     * @param newPassword new password for User
     */
    public void setPassword(String newPassword) { password = newPassword; }

    /**
     * 2-arg constructor that creates a User with an email and password
     * @param newUsername User's email
     * @param newPassword User's password
     */
    public User(String newUsername, String newPassword) {
        username = newUsername;
        password = newPassword;
    }

    /**
     * toString method used to visualize the User's email and password
     * @return a String containing the User's email and password
     */
    @Override
    public String toString() { return username + " | " + password; }


    /* ***************************************************** */
    private User(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
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
