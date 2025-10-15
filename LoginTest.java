package com.quickchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test void testValidUsername() {
        Login l = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(l.checkUserName());
    }

    @Test void testInvalidUsername() {
        Login l = new Login("kyle!", "Password1!", "+27838968976");
        assertFalse(l.checkUserName());
    }

    @Test void testPasswordComplexityValid() {
        Login l = new Login("neo_", "Password1!", "+27838968976");
        assertTrue(l.checkPasswordComplexity());
    }

    @Test void testPasswordComplexityInvalid() {
        Login l = new Login("neo_", "password", "+27838968976");
        assertFalse(l.checkPasswordComplexity());
    }

    @Test void testCellNumberValid() {
        Login l = new Login("neo_", "Password1!", "+27838968976");
        assertTrue(l.checkCellPhoneNumber());
    }

    @Test void testCellNumberInvalid() {
        Login l = new Login("neo_", "Password1!", "083759");
        assertFalse(l.checkCellPhoneNumber());
    }

    @Test void testLoginSuccess() {
        Login l = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(l.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test void testLoginFail() {
        Login l = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(l.loginUser("kyl_1", "wrongpass"));
    }
}
