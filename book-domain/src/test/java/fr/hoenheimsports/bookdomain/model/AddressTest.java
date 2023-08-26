package fr.hoenheimsports.bookdomain.model;

import fr.hoenheimsports.bookdomain.model.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddressTest {
    @Test
    void testStreetNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Address(null, 1234, "Paris"));

        String expectedMessage = "street should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCityNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Address("Rue de la République", 1234, null));

        String expectedMessage = "city should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testPostalCodeNonNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Address("Rue de la République", -1234, "Paris"));

        String expectedMessage = "postalCode must be equal or greater than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}