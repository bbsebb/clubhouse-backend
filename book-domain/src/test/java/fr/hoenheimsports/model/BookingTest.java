package fr.hoenheimsports.model;

import fr.hoenheimsports.bookdomain.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingTest {
    @Test
    void testIdNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Booking(null,
                    new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                    new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                    new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                    BookingState.VALIDATED);
        });

        String expectedMessage = "stard should not be null"; // Peut-être une coquille dans votre message d'erreur original ?
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testHallNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Booking(UUID.randomUUID(),
                    null,
                    new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                    new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                    BookingState.VALIDATED);
        });

        String expectedMessage = "hall should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUserNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Booking(UUID.randomUUID(),
                    new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                    null,
                    new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                    BookingState.VALIDATED);
        });

        String expectedMessage = "user should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testTimeslotNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Booking(UUID.randomUUID(),
                    new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                    new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                    null,
                    BookingState.VALIDATED);
        });

        String expectedMessage = "timeslot should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStateNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Booking(UUID.randomUUID(),
                    new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                    new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                    new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                    null);
        });

        String expectedMessage = "state should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}