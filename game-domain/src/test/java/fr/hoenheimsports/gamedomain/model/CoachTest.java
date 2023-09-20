package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoachTest {
    @Test
    void testValidCoachCreation() {
        UUID id = UUID.randomUUID();
        Coach coach = new Coach(id, "John Doe", new PhoneNumber("0123456789"));
        assertEquals(id, coach.id());
        assertEquals("John Doe", coach.name());
        assertEquals("0123456789", coach.phoneNumber().phoneNumber());
    }

    @Test
    void testUnknownCoach() {
        Coach coach = Coach.UNKNOWN;
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), coach.id());
        assertEquals("unknown", coach.name());
        assertEquals("00", coach.phoneNumber().phoneNumber());
    }

    @Test
    void testNullIdThrowsException() {
        assertThrows(NullPointerException.class, () -> new Coach(null, "John Doe", new PhoneNumber("0123456789")));
    }

    @Test
    void testNullNameThrowsException() {
        UUID id = UUID.randomUUID();
        assertThrows(NullPointerException.class, () -> new Coach(id, null, new PhoneNumber("0123456789")));
    }

    @Test
    void testNullPhoneNumberThrowsException() {
        UUID id = UUID.randomUUID();
        assertThrows(NullPointerException.class, () -> new Coach(id, "John Doe", null));
    }
}