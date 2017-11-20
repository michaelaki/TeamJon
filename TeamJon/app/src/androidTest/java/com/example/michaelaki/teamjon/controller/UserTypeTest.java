package com.example.michaelaki.teamjon.controller;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.michaelaki.teamjon.model.Admin;
import android.content.Intent;
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
        assertTrue("User type should be admin", activity.getUserType(true) instanceof Admin);
        assertTrue("User type should not be admin but user", !(activity.getUserType(false) instanceof Admin));
        assertTrue("User type should be user", activity.getUserType(false) != null);
    }

}