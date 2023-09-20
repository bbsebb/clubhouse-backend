package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TeamColorTest {
    @Test
    void testGetFrenchName() {
        assertEquals("ROUGE", TeamColor.RED.getFrenchName());
    }

    @Test
    void testGetByFrenchNameValid() {
        assertEquals(TeamColor.RED, TeamColor.getByFrenchName("ROUGE"));
    }

    @Test
    void testGetByFrenchNameInvalid() {
        assertThrows(NoSuchElementException.class, () -> TeamColor.getByFrenchName("NOT_A_COLOR"));
    }
}