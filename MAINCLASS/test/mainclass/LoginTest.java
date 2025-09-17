/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package mainclass;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_lab
 */
public class LoginTest {
    Login login = new Login("Neo", "Nkedi");

    // Username tests
    @Test
    void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testInvalidUsername() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // Password tests
    @Test
    void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Cell phone tests
    @Test
    void testValidCell() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testInvalidCell() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // Registration flow
    @Test
    void testRegisterUserSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully!", result);
    }

    @Test
    void testRegisterUserBadUsername() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    void testRegisterUserBadPassword() {
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    void testRegisterUserBadCell() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    // Login flow
    @Test
    void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void testLoginFail() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
    }

    @Test
    void testReturnLoginStatusSuccess() {
        assertEquals("Welcome Neo Nkedi it is great to see you again.", login.returnLoginStatus(true));
    }

    @Test
    void testReturnLoginStatusFail() {
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus(false));
    }
}

