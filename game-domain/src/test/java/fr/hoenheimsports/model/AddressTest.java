package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Address(null, 12345, "City"));
        assertThrows(NullPointerException.class, () -> new Address("Street", 12345, null));
        assertThrows(IllegalArgumentException.class, () -> new Address("Street", -1, "City"));
    }
}