package fr.hoenheimsports.bookdomain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private Hall hall;
    private Tenant tenant1;
    private Booking bookingUnderTest, otherBooking1, otherBooking2;

    @BeforeEach
    void setUp() {
        Address address = new Address("123 Main St", 75001, "Paris");
        hall = new Hall(UUID.randomUUID(), "Hall 1", address, 100);
        tenant1 = new Tenant(UUID.randomUUID(), "Tenant1", "tenant1@example.com", address, true);
        Tenant tenant2 = new Tenant(UUID.randomUUID(), "Tenant2", "tenant2@example.com", address, false);

        // Booking being tested
        bookingUnderTest = new Booking(UUID.randomUUID(), hall, tenant1,
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
                BookingState.PENDING,
                Payment.UNKNOWN, "use");

        // Other bookings
        otherBooking1 = new Booking(UUID.randomUUID(), hall, tenant2,
                new Timeslot(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)),
                BookingState.ACCEPTED,
                Payment.UNKNOWN, "use");

        otherBooking2 = new Booking(UUID.randomUUID(), hall, tenant2,
                new Timeslot(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5)),
                BookingState.ACCEPTED,
                Payment.UNKNOWN, "use");
    }
    @Test
    void testIdNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(null,
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.VALIDATED, Payment.UNKNOWN, "use"));

        String expectedMessage = "start should not be null"; // Peut-être une coquille dans votre message d'erreur original ?
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testHallNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                null,
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.VALIDATED, Payment.UNKNOWN, "use"));

        String expectedMessage = "hall should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUserNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                null,
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.VALIDATED, Payment.UNKNOWN, "use"));

        String expectedMessage = "user should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testTimeslotNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                null,
                BookingState.VALIDATED, Payment.UNKNOWN, "use"));

        String expectedMessage = "timeslot should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStateNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                null, Payment.UNKNOWN, "use"));

        String expectedMessage = "state should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testPaymentNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.VALIDATED, null, "use"));

        String expectedMessage = "payment should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUseNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.VALIDATED, Payment.UNKNOWN, null));

        String expectedMessage = "use should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void toPrice_shouldThrowException_whenBookingIsAlreadyPaid() {
        Payment initialPayment = new Payment(BigDecimal.valueOf(100), true, PaymentType.CASH, UUID.randomUUID());
        Booking booking = new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.PENDING, initialPayment, "use");

        assertThrows(IllegalStateException.class, () -> booking.toPrice(BigDecimal.valueOf(150)));
    }

    @Test
    void toPrice_shouldUpdatePaymentAmount_whenBookingIsNotPaid() {
        Payment initialPayment = new Payment(BigDecimal.valueOf(100), false, PaymentType.CASH, UUID.randomUUID());
        Booking booking = new Booking(UUID.randomUUID(),
                new Hall(UUID.randomUUID(), "name", new Address("Street", 1234, "City"), 50),
                new AssociationHallUser(UUID.randomUUID(), "username", "email@example.com"),
                new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
                BookingState.PENDING, initialPayment, "use");

        booking.toPrice(BigDecimal.valueOf(150));

        assertEquals(BigDecimal.valueOf(150), booking.getPayment().amount());
    }

    @Test
    void testHasTimeslotFree_NoOverlap() {
        List<Booking> bookings = List.of(otherBooking2);
        assertTrue(bookingUnderTest.hasTimeslotFree(bookings));
    }

    @Test
    void testHasTimeslotFree_OverlapButAllowed() {
        otherBooking1.setAllowsOverlap(true);
        List<Booking> bookings = List.of(otherBooking1);
        assertTrue(bookingUnderTest.hasTimeslotFree(bookings));
    }

    @Test
    void testHasTimeslotFree_OverlapNotAllowed() {
        List<Booking> bookings = List.of(otherBooking1);
        assertFalse(bookingUnderTest.hasTimeslotFree(bookings));
    }

    @Test
    void testHasTimeslotFree_SameUser() {
        otherBooking1 = new Booking(UUID.randomUUID(), hall, tenant1,
                new Timeslot(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)),
                BookingState.ACCEPTED,
                Payment.UNKNOWN, "use");

        List<Booking> bookings = List.of(otherBooking1);
        assertTrue(bookingUnderTest.hasTimeslotFree(bookings));
    }

    @Test
    void testHasTimeslotFree_InactiveBooking() {
        otherBooking1.setState(BookingState.REFUSED);
        List<Booking> bookings = List.of(otherBooking1);
        assertTrue(bookingUnderTest.hasTimeslotFree(bookings));
    }

}