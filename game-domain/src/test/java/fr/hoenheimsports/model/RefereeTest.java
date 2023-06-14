package fr.hoenheimsports.model;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Referee(null, "Name", new PhoneNumber("123456789")));
        assertThrows(NullPointerException.class, () -> new Referee(UUID.randomUUID(), null, new PhoneNumber("123456789")));
        assertThrows(NullPointerException.class, () -> new Referee(UUID.randomUUID(), "Name", null));
    }
}