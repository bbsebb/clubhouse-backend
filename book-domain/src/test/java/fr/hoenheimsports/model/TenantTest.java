package fr.hoenheimsports.model;
import fr.hoenheimsports.bookdomain.model.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TenantTest {
    @Test
    void testIdNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Tenant(null, "username", "email@example.com", new Address("Street", 1234, "City"));
        });

        String expectedMessage = "id should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUsernameNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Tenant(UUID.randomUUID(), null, "email@example.com", new Address("Street", 1234, "City"));
        });

        String expectedMessage = "username should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEmailNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Tenant(UUID.randomUUID(), "username", null, new Address("Street", 1234, "City"));
        });

        String expectedMessage = "email should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAddressNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Tenant(UUID.randomUUID(), "username", "email@example.com", null);
        });

        String expectedMessage = "address should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}