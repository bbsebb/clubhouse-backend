package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.api.EmailService;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingPayImplTest {
    private BookingPayImpl bookingPayImpl;
    private BookingStub bookingStub;
    private EmailServiceStub emailServiceStub;

    @BeforeEach
    void setUp() {
        bookingStub = new BookingStub();
        emailServiceStub = new EmailServiceStub();
        bookingPayImpl = new BookingPayImpl(bookingStub, emailServiceStub);
    }

    @Test
    void testPay() {
        UUID bookingId = UUID.randomUUID();
        Timeslot timeslot = new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Address hallAddress = new Address("123 Street", 66677, "12345");  // Assuming an Address class exists.
        Hall hall = new Hall(UUID.randomUUID(), "Test Hall", hallAddress, 100);
        HallUser user = new AssociationHallUser(UUID.randomUUID(), "Test User", "test@example.com");
        Booking booking = new Booking(bookingId, hall, user, timeslot, BookingState.PENDING, Payment.UNKNOWN, false, "Test Use");
        bookingStub.save(booking);

        Booking updatedBooking = bookingPayImpl.pay(bookingId, PaymentType.BANQUE_CARD, UUID.randomUUID());

        assertEquals(BookingState.VALIDATED, updatedBooking.getState());
        assertTrue(emailServiceStub.isEmailSentToUser());
        assertTrue(emailServiceStub.isEmailSentToRecipient());
    }

    private static class EmailServiceStub implements EmailService {
        private boolean emailSentToUser = false;
        private boolean emailSentToRecipient = false;

        @Override
        public void sendEmailNotifyBookingToUser(Booking booking) {
            emailSentToUser = true;
        }

        @Override
        public void sendEmailNotifyBookingToRecipient(Booking booking) {
            emailSentToRecipient = true;
        }

        public boolean isEmailSentToUser() {
            return emailSentToUser;
        }

        public boolean isEmailSentToRecipient() {
            return emailSentToRecipient;
        }
    }
}