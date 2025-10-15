package com.quickchat;

import java.util.regex.Pattern;

public class Login {
    private String username;
    private String password;
    private String cellNumber;
    private boolean loggedIn = false;

    public Login(String username, String password, String cellNumber) {
        this.username = username == null ? "" : username;
        this.password = password == null ? "" : password;
        this.cellNumber = cellNumber == null ? "" : cellNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*\\d.*")) return false;
        if (!password.matches(".*[!@#$%^&*()_+\-=[\]{};':\"\\|,.<>/?].*")) return false;
        return true;
    }

    public boolean checkCellPhoneNumber() {
        return Pattern.matches("^\\+27\\d{9}$", cellNumber);
    }

    public String registerUser() {
        StringBuilder sb = new StringBuilder();
        if (!checkUserName())
            sb.append("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.\n");
        else
            sb.append("Username successfully captured.\n");

        if (!checkPasswordComplexity())
            sb.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
        else
            sb.append("Password successfully captured.\n");

        if (!checkCellPhoneNumber())
            sb.append("Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
        else
            sb.append("Cell number successfully captured.");
        return sb.toString();
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        loggedIn = enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
        return loggedIn;
    }

    public String returnLoginStatus(String firstName, String lastName) {
        return loggedIn
                ? "Welcome " + firstName + " ," + lastName + " it is great to see you again."
                : "Username or password incorrect, please try again.";
    }
}
