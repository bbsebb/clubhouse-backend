package fr.hoenheimsports.gamedomain.model;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Referee(null, "Name"));
        assertThrows(NullPointerException.class, () -> new Referee(UUID.randomUUID(), null));
        assertDoesNotThrow( () -> new Referee(UUID.randomUUID(), "Name"));
    }
}