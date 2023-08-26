package fr.hoenheimsports.bookdomain.model;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssociationHallUserTest {

    @Test
    void testIdNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new AssociationHallUser(null, "username", "email@example.com"));

        String expectedMessage = "id should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUsernameNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new AssociationHallUser(UUID.randomUUID(), null, "email@example.com"));

        String expectedMessage = "username should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEmailNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new AssociationHallUser(UUID.randomUUID(), "username", null));

        String expectedMessage = "email should be not null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}