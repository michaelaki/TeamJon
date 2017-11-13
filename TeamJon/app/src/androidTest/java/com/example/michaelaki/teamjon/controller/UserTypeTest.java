package com.example.michaelaki.teamjon.controller;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.michaelaki.teamjon.model.Admin;
import com.example.michaelaki.teamjon.model.User;
import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * File made by JonathanChen on 11/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class UserTypeTest extends ActivityTestRule {
    public UserTypeTest() {
        super(RegisterActivity.class);
    }

    @Test
    public void getUserType() throws Exception {
        launchActivity(new Intent());
        RegisterActivity activity = (RegisterActivity) getActivity();
        boolean isAdmin = true;
        boolean notAdmin = false;
        assertTrue("User type should be admin", activity.getUserType(isAdmin) instanceof Admin);
        assertTrue("User type should not be admin but user", !(activity.getUserType(notAdmin) instanceof Admin));
        assertTrue("User type should be user", activity.getUserType(notAdmin) instanceof User);
    }

}