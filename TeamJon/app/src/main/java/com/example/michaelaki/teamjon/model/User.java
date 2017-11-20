package com.example.michaelaki.teamjon.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * File made by quintinroper on 9/18/17.
 */

public class User implements Parcelable, Serializable {
    private String username;
    private String password;
    private String name;

    /**
     * Getter for username
     * @return User's username
     */
    public String getUsername() { return username; }

    /**
     * Getter for password
     * @return User's password
     */
    public String getPassword() { return password; }

    /**
<<<<<<< HEAD
     * Setter for password
     * @param newPassword new password for User
     */
    public void setPassword(String newPassword) { password = newPassword; }

    public boolean validatePassword(String newPassword) { return (newPassword.length() > 6); }

    /**
=======
>>>>>>> d7f8fc43806b9dd2018f69628ca477bc16956977
     * Getter for name
     * @return User's name
     */
    public String getName() { return name; }

    /**
     * 2-arg constructor that creates a User with an email and password
     * @param newUsername User's email
     * @param newPassword User's password
     * @param newName User's name
     */
    public User(String newUsername, String newPassword, String newName) {
        username = newUsername;
        password = newPassword;
        name = newName;
    }

    /**
     * toString method used to visualize the User's email and password
     * @return a String containing the User's email and password
     */
    @Override
    public String toString() { return name + " | " + username + " | " + password; }


    /* ***************************************************** */
    private User(Parcel in) {
        username = in.readString();
        password = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(name);
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
