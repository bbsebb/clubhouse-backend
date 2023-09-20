package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HalleTest {
    @Test
    void testValidHall() {
        UUID id = UUID.randomUUID();
        Address address = new Address("123 Street", 12345, "City"); // Hypothèse sur la structure de Address.
        GlueAuthorization glueAuthorization = GlueAuthorization.UNKNOWN; // Je n'ai pas de détails sur GlueAuthorization, donc j'utilise UNKNOWN comme exemple.

        Hall hall = new Hall(id, "Hall Name", address, glueAuthorization);

        assertEquals(id, hall.id());
        assertEquals("Hall Name", hall.name());
        assertEquals(address, hall.address());
        assertEquals(glueAuthorization, hall.glueAuthorization());
    }

    @Test
    void testUnknownHall() {
        Hall hall = Hall.UNKNOWN;
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), hall.id());
        assertEquals("unknown", hall.name());
        assertEquals(Address.UNKNOWN, hall.address());
        assertEquals(GlueAuthorization.UNKNOWN, hall.glueAuthorization());
    }

    @Test
    void testNullIdThrowsException() {
        assertThrows(NullPointerException.class, () -> new Hall(null, "Name", new Address("123 Street", 12345, "City"), GlueAuthorization.UNKNOWN));
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(NullPointerException.class, () -> new Hall(UUID.randomUUID(), null, new Address("123 Street", 12345, "City"), GlueAuthorization.UNKNOWN));
    }

    @Test
    void testNullAddressThrowsException() {
        assertThrows(NullPointerException.class, () -> new Hall(UUID.randomUUID(), "Name", null, GlueAuthorization.UNKNOWN));
    }

    @Test
    void testNullGlueAuthorizationThrowsException() {
        assertThrows(NullPointerException.class, () -> new Hall(UUID.randomUUID(), "Name", new Address("123 Street", 12345, "City"), null));
    }
}