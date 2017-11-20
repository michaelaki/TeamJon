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
public class ValidLatLngTest extends ActivityTestRule {
    public ValidLatLngTest() {
        super(AddRatActivity.class);
    }

    @Test
    public void isValidLatLngTest() throws Exception {
        launchActivity(new Intent());
        AddRatActivity activity = (AddRatActivity) getActivity();
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("-180.0", false)));
        assertTrue("Longitude value must be greater than -180", (!activity.isValidLatLng("-181.0", false)));
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("-180", false)));
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("-179.9", false)));
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("0", false)));
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("180", false)));
        assertTrue("Longitude value is between -180 and 180", (activity.isValidLatLng("179.9", false)));
        assertTrue("Longitude value must be less than 180", (!activity.isValidLatLng("181", false)));
        assertTrue("Latitude value is between -180 and 180", (activity.isValidLatLng("-180.0", true)));
        assertTrue("Latitude value must be greater than -180", (!activity.isValidLatLng("-181.0", true)));
        assertTrue("Latitude value is between -180 and 180", (activity.isValidLatLng("-180", true)));
        assertTrue("Latitude value is between -180 and 180", (activity.isValidLatLng("-179.9", true)));
        assertTrue("Latitude value is between -180 and 180", (activity.isValidLatLng("0", true)));
        assertTrue("LLatitude value is between -180 and 180", (activity.isValidLatLng("180", true)));
        assertTrue("Latitude value is between -180 and 180", (activity.isValidLatLng("179.9", true)));
        assertTrue("Latitude value must be less than 180", (!activity.isValidLatLng("181", true)));
    }

}