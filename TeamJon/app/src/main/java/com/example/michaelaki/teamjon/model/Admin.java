package com.example.michaelaki.teamjon.model;

/**
 * File made by quintinroper on 9/24/17.
 */

public class Admin extends User {
    /**
     * 2-arg constructor that creates a Admin with an username and password
     *
     * @param newEmail    Admin's username
     * @param newPassword Admin's password
     * @param newName     Admin's name
     */
    public Admin(String newEmail, String newPassword, String newName) {
        super(newEmail, newPassword, newName);
    }

    /**
     * toString method used to visualize the Admin's username and password
     * @return a String containing the Admin's username and password
     */
    @Override
    public String toString() { return "Admin: " + super.toString(); }
}
