package com.example.michaelaki.teamjon.model;

/**
 * Created by quintinroper on 9/24/17.
 */

public class Admin extends User {
    /**
     * 2-arg constructor that creates a Admin with an email and password
     *
     * @param newEmail    Admin's email
     * @param newPassword Admin's password
     */
    public Admin(String newEmail, String newPassword) {
        super(newEmail, newPassword);
    }

    /**
     * toString method used to visualize the Admin's email and password
     * @return a String containing the Admin's email and password
     */
    @Override
    public String toString() { return "Admin: " + getEmail() + " | " + getPassword(); }
}
