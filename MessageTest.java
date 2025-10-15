package com.quickchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageTest {
    @Test void testRecipientValid() {
        Message m = new Message();
        assertTrue(m.checkRecipientNumber("+27838968976"));
    }

    @Test void testRecipientInvalid() {
        Message m = new Message();
        assertFalse(m.checkRecipientNumber("083759"));
    }

    @Test void testMessageLengthValid() {
        Message m = new Message();
        assertEquals("Message ready to send.", m.checkMessageLength("Short message"));
    }

    @Test void testMessageLengthInvalid() {
        Message m = new Message();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) sb.append("a");
        assertTrue(m.checkMessageLength(sb.toString()).startsWith("Message exceeds 250"));
    }

    @Test void testCreateMessageHash() {
        Message m = new Message();
        m.setMessageID("0012345678");
        m.setMessageText("Hi Mike can you join us for dinner tonight");
        assertEquals("00:0:HITONIGHT", m.createMessageHash(0));
    }

    @Test void testSendOptions() {
        Message m = new Message();
        assertEquals("Message successfully sent.", m.sendMessageOption(1));
        assertEquals("Press 0 to delete message.", m.sendMessageOption(2));
        assertEquals("Message successfully stored.", m.sendMessageOption(3));
    }
}
