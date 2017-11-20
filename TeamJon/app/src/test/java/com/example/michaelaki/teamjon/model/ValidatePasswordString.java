package com.example.michaelaki.teamjon.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by varunsubbiah on 11/19/17.
 */

public class ValidatePasswordString {

    @Test
    public void testValidatePassword() {
        User test = new User("", "", "");
        String test1 = null;
        String test2 = "";
        String test3 = "hello";
        String test4 = "hello1";
        String test5 = "hello12";
        assertTrue(!test.validatePassword(test1));
        assertTrue(!test.validatePassword(test2));
        assertTrue(!test.validatePassword(test3));
        assertTrue(!test.validatePassword(test4));
        assertTrue(test.validatePassword(test5));
    }
}
