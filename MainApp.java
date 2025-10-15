package com.quickchat;

import javax.swing.JOptionPane;

public class MainApp {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        String username = JOptionPane.showInputDialog("Enter username (must contain '_' and ≤ 5 characters):");
        String password = JOptionPane.showInputDialog("Enter password (min 8 chars, 1 capital, 1 number, 1 special char):");
        String cell = JOptionPane.showInputDialog("Enter cell number (+27XXXXXXXXX):");

        Login login = new Login(username, password, cell);
        JOptionPane.showMessageDialog(null, login.registerUser());

        if (!login.checkUserName() || !login.checkPasswordComplexity() || !login.checkCellPhoneNumber()) {
            JOptionPane.showMessageDialog(null, "Registration failed. Restart the application.");
            System.exit(0);
        }

        String uTry = JOptionPane.showInputDialog("Login - username:");
        String pTry = JOptionPane.showInputDialog("Login - password:");
        boolean ok = login.loginUser(uTry, pTry);
        JOptionPane.showMessageDialog(null, login.returnLoginStatus("Neo", "Nkedi"));
        if (!ok) System.exit(0);

        Message msg = new Message();
        int opt;
        do {
            String input = JOptionPane.showInputDialog("1) Send Messages\n2) Coming Soon\n3) Quit");
            if (input == null) break;
            opt = Integer.parseInt(input);
            switch (opt) {
                case 1:
                    int n = Integer.parseInt(JOptionPane.showInputDialog("How many messages?"));
                    for (int i = 0; i < n; i++) {
                        String r = JOptionPane.showInputDialog("Recipient (+27...)");
                        if (!msg.checkRecipientNumber(r)) {
                            JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code.");
                            continue;
                        }
                        String m = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                        String res = msg.checkMessageLength(m);
                        JOptionPane.showMessageDialog(null, res);
                        if (!res.equals("Message ready to send.")) continue;
                        msg.setRecipient(r);
                        msg.setMessageText(m);
                        String id = msg.generateMessageID();
                        String hash = msg.createMessageHash(msg.returnTotalMessages());
                        int c = Integer.parseInt(JOptionPane.showInputDialog("1) Send Message\n2) Disregard\n3) Store"));
                        String rmsg = msg.sendMessageOption(c);
                        JOptionPane.showMessageDialog(null, rmsg + "\nMessageID: " + id + "\nMessage Hash: " + hash + "\nRecipient: " + r + "\nMessage: " + m);
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;
            }
        } while (opt != 3);
    }
}
