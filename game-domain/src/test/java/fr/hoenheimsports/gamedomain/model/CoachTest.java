package fr.hoenheimsports.gamedomain.model;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Coach(null, "Name", new PhoneNumber("123456789")));
        assertThrows(NullPointerException.class, () -> new Coach(UUID.randomUUID(), null, new PhoneNumber("123456789")));
        assertThrows(NullPointerException.class, () -> new Coach(UUID.randomUUID(), "Name", null));
    }
}