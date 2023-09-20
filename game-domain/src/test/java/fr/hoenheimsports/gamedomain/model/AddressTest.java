package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {
    @Test
    void testValidAddress() {
        Address address = new Address("Main St", 12345, "CityName");
        assertEquals("Main St", address.street());
        assertEquals(12345, address.postalCode());
        assertEquals("CityName", address.city());
    }

    @Test
    void testUnknownAddress() {
        Address address = Address.UNKNOWN;
        assertEquals("unknown", address.street());
        assertEquals(0, address.postalCode());
        assertEquals("unknown", address.city());
    }

    @Test
    void testNullStreetThrowsException() {
        assertThrows(NullPointerException.class, () -> new Address(null, 12345, "CityName"));
    }

    @Test
    void testNullCityThrowsException() {
        assertThrows(NullPointerException.class, () -> new Address("Main St", 12345, null));
    }

    @Test
    void testNegativePostalCodeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Main St", -12345, "CityName"));
    }
}