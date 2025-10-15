package com.quickchat;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private static int sentMessageCount = 0;

    public String generateMessageID() {
        long id = ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
        this.messageID = Long.toString(id);
        return this.messageID;
    }

    public void setMessageID(String id) { this.messageID = id; }
    public void setMessageText(String text) { this.messageText = text; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public boolean checkRecipientNumber(String recipient) {
        return recipient != null && Pattern.matches("^\\+27\\d{9}$", recipient);
    }

    public String checkMessageLength(String messageText) {
        if (messageText == null) messageText = "";
        int len = messageText.length();
        return len <= 250 ? "Message ready to send." : "Message exceeds 250 characters by " + (len - 250) + ", please reduce size.";
    }

    public String createMessageHash(int messageNumber) {
        if (messageID == null || messageText == null) throw new IllegalStateException("Set messageID and messageText first");
        String first2 = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        return (first2 + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String sendMessageOption(int choice) {
        switch (choice) {
            case 1: sentMessageCount++; return "Message successfully sent.";
            case 2: return "Press 0 to delete message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid option.";
        }
    }

    public int returnTotalMessages() { return sentMessageCount; }
}
