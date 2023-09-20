package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {
    @Test
    void testValidClub() {
        String code = UUID.randomUUID().toString();
        Hall hall1 = new Hall(UUID.randomUUID(), "Hall1", new Address("123 Street", 12345, "City"), GlueAuthorization.UNKNOWN);
        Hall hall2 = new Hall(UUID.randomUUID(), "Hall2", new Address("456 Street", 67890, "City"), GlueAuthorization.UNKNOWN);
        Set<Hall> halls = Set.of(hall1, hall2);

        Club club = new Club(code, "Club Name", halls);

        assertEquals(code, club.code());
        assertEquals("Club Name", club.name());
        assertEquals(halls, club.halls());
    }

    @Test
    void testUnknownClub() {
        Club club = Club.UNKNOWN;

        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000").toString(), club.code());
        assertEquals("unknown", club.name());
        assertEquals(Set.of(Hall.UNKNOWN), club.halls());
    }

    @Test
    void testNullCodeThrowsException() {
        assertThrows(NullPointerException.class, () -> new Club(null, "Name", Set.of(Hall.UNKNOWN)));
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(NullPointerException.class, () -> new Club(UUID.randomUUID().toString(), null, Set.of(Hall.UNKNOWN)));
    }

    @Test
    void testNullHallsThrowsException() {
        assertThrows(NullPointerException.class, () -> new Club(UUID.randomUUID().toString(), "Name", null));
    }

    @Test
    void testEmptyHallsSet() {
        String code = UUID.randomUUID().toString();
        Club club = new Club(code, "Club Name", Set.of());

        assertEquals(code, club.code());
        assertEquals("Club Name", club.name());
        assertTrue(club.halls().isEmpty());
    }
}