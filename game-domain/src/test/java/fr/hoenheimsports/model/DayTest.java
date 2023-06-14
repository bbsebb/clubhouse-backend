package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {
    @Test
    public void testConstructorWithInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Day(-1));
    }
}