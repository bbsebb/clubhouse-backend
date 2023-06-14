package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Club(null, "Name"));
        assertThrows(NullPointerException.class, () -> new Club("Code", null));
    }
}