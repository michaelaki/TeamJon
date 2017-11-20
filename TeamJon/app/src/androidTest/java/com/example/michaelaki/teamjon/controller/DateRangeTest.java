package com.example.michaelaki.teamjon.controller;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * File made by quintinroper on 11/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class DateRangeTest extends ActivityTestRule {
    public DateRangeTest() {
        super(FilterActivity.class);
    }

    @Test
    public void validateDateRange() throws Exception {
        launchActivity(new Intent());
        FilterActivity activity = (FilterActivity) getActivity();
        long before2016 = 1451538000000L;
        long afterToday = 1514782800000L;
        long aug1 = 1501560000000L;
        long aug2 = 1501646400000L;
        assertTrue("Valid Start Date before valid End Date should pass",
                activity.validateDateRange(aug1, aug2));
        assertFalse("Valid Start Date and valid End Date on same day should fail",
                activity.validateDateRange(aug1, aug1));
        assertFalse("Start Date before 2016 should fail",
                activity.validateDateRange(before2016, aug1));
        assertFalse("End Date after today should fail",
                activity.validateDateRange(aug1, afterToday));
    }

}