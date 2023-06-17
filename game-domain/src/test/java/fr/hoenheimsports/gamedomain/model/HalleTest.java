package fr.hoenheimsports.gamedomain.model;

import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Halle;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HalleTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Halle(null, "Name", Address.UNKNOWN, GlueAuthorization.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Halle(UUID.randomUUID(), null, Address.UNKNOWN, GlueAuthorization.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Halle(UUID.randomUUID(), "Name", null, GlueAuthorization.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Halle(UUID.randomUUID(), "Name", Address.UNKNOWN, null));
    }
}