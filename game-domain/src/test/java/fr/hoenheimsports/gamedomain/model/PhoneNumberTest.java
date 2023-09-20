package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberTest {
    @Test
    void testValidPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("0123456789");
        assertEquals("0123456789", phoneNumber.phoneNumber());
    }

    @Test
    void testUnknownPhoneNumber() {
        PhoneNumber phoneNumber = PhoneNumber.UNKNOWN;
        assertEquals("unknown", phoneNumber.phoneNumber());
    }

    @Test
    void testNullPhoneNumberThrowsException() {
        assertThrows(NullPointerException.class, () -> new PhoneNumber(null));
    }

}