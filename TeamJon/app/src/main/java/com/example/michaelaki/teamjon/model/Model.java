package com.example.michaelaki.teamjon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quintinroper on 9/19/17.
 */

public class Model {
    private static final Model instance = new Model();
    public static Model getInstance() { return instance; }

    private List<User> users;

    private User currentUser;

    private Model() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() { return users; }

    public boolean addUser(User tempUser) {
        for (User c : users ) {
            if (c.getEmail().equals(tempUser.getEmail())) return false;
        }
        users.add(tempUser);
        return true;
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User tempUser) { currentUser = tempUser; }
}
