package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.spi.stub.EmailProviderStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceImplTest {
    private EmailProviderStub emailProviderStub;
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        emailProviderStub = new EmailProviderStub();
        emailService = new EmailServiceImpl(emailProviderStub);
    }

    @Test
    void sendEmailNotifyBookingToUserTest() {
        Booking booking = createSampleBooking();
        emailService.sendEmailNotifyBookingToUser(booking);
        EmailProviderStub.Email email = emailProviderStub.getEmails().get(0);
        assertEquals(booking.getUser().getEmail(), email.to());
        assertEquals(String.format("Réservation n° %s", booking.getId().toString()), email.subject());
    }

    @Test
    void sendEmailNotifyBookingToRecipientTest() {
        Booking booking = createSampleBooking();
        emailService.sendEmailNotifyBookingToRecipient(booking);
        EmailProviderStub.Email email = emailProviderStub.getEmails().get(0);
        assertEquals(emailProviderStub.findRecipient(), email.to());
        assertEquals(String.format("Réservation n° %s", booking.getId().toString()), email.subject());
    }

    private Booking createSampleBooking() {
        Hall hall = new Hall(UUID.randomUUID(), "Sample Hall", new Address("123 Street", 66670, "Country"), 100);
        HallUser user = new AssociationHallUser(UUID.randomUUID(), "Test User", "test@example.com");
        Timeslot timeslot = new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        BookingState state = BookingState.ACCEPTED;
        Payment payment = new Payment(BigDecimal.valueOf(100.0), false, PaymentType.BANQUE_CARD, UUID.randomUUID());
        String use = "Sample Usage";
        boolean allowsOverlap = true;

        return new Booking(UUID.randomUUID(), hall, user, timeslot, state, payment, allowsOverlap, use);
    }

}