package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingDisplayImplTest {

    private BookingDisplayImpl bookingDisplay;
    private Timeslot timeslot1, timeslot2;
    private Booking booking1, booking2;

    @BeforeEach
    void setUp() {
        // Initialiser les objets nécessaires
        Address address1 = new Address("123 Main St", 75001, "Paris");
        AssociationHallUser user1 = new AssociationHallUser(UUID.randomUUID(), "User1", "user1@example.com");
        Tenant tenant = new Tenant(UUID.randomUUID(), "Tenant", "tenant@example.com", address1, true);
        Hall hall1 = new Hall(UUID.randomUUID(), "Hall 1", address1, 100);
        Payment payment1 = Payment.UNKNOWN;

        timeslot1 = new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        timeslot2 = new Timeslot(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));

        booking1 = new Booking(UUID.randomUUID(), hall1, user1, timeslot1, BookingState.PENDING, payment1, true, "use");
        booking2 = new Booking(UUID.randomUUID(), hall1, tenant, timeslot2, BookingState.PENDING, payment1,true , "use");

        BookingStub bookingRepository = new BookingStub();
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);

        bookingDisplay = new BookingDisplayImpl(bookingRepository);
    }

    @Test
    void testFindAll() {
        List<Booking> result = bookingDisplay.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(booking1));
        assertTrue(result.contains(booking2));
    }

    @Test
    void testFindByTimeslot() {
        List<Booking> result = bookingDisplay.findByTimeslot(timeslot1);
        assertEquals(1, result.size());
        assertTrue(result.contains(booking1));

        result = bookingDisplay.findByTimeslot(timeslot2);
        assertEquals(1, result.size());
        assertTrue(result.contains(booking2));
    }

    @Test
    void testFindById() {
        Optional<Booking> result = bookingDisplay.findById(booking1.getId());
        assertTrue(result.isPresent());
        assertEquals(booking1, result.get());
    }
}