package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FDMETest {
    @Test
    public void testConstructorWithNullParameter() {
        assertThrows(NullPointerException.class, () -> new FDME(null));
    }
}