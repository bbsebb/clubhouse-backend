package fr.hoenheimsports.bookdomain.model;
import fr.hoenheimsports.bookdomain.model.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HallTest {
    @Test
    void testIdNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Hall(null, "name", new Address("Street", 1234, "City"), 50));

        String expectedMessage = "start should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testNameNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Hall(UUID.randomUUID(), null, new Address("Street", 1234, "City"), 50));

        String expectedMessage = "name should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAddressNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Hall(UUID.randomUUID(), "name", null, 50));

        String expectedMessage = "address should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCapacityPositive() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), -50));

        String expectedMessage = "capacity should be great than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}