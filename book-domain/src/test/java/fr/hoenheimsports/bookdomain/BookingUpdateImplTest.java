package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.api.EmailService;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;
import fr.hoenheimsports.bookdomain.spi.EmailProvider;
import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import fr.hoenheimsports.bookdomain.spi.stub.EmailProviderStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingUpdateImplTest {

    private BookingRepository bookingRepository;
    private EmailService emailService;
    private BookingUpdateImpl bookingUpdate;
    private EmailProviderStub emailProviderStub;
    private UUID sampleBookingId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        emailProviderStub = new EmailProviderStub();
        bookingRepository = new BookingStub(); // Supposons que vous ayez une implémentation en mémoire pour les tests
        emailService = new EmailServiceImpl(emailProviderStub);
        bookingUpdate = new BookingUpdateImpl(bookingRepository, emailService);
    }

    @Test
    void testAcceptBooking() {
        UUID sampleBookingId = UUID.randomUUID();
        Booking sampleBooking = createSampleBooking(sampleBookingId, BookingState.PENDING);
        bookingRepository.save(sampleBooking);

        Booking updatedBooking = bookingUpdate.accept(sampleBookingId);

        assertEquals(BookingState.ACCEPTED, updatedBooking.getState());
        assertFalse(emailProviderStub.getEmails().isEmpty()); // Supposant que des e-mails sont envoyés lors de l'acceptation
    }

    @Test
    void testRefuseBooking() {
        Booking sampleBooking = createSampleBooking(sampleBookingId, BookingState.PENDING);
        bookingRepository.save(sampleBooking);

        Booking updatedBooking = bookingUpdate.refuse(sampleBookingId);

        assertEquals(BookingState.REFUSED, updatedBooking.getState());
        assertFalse(emailProviderStub.getEmails().isEmpty()); // Assuming emails are sent for refusal
    }

    @Test
    void testCancelBooking() {
        Booking sampleBooking = createSampleBooking(sampleBookingId, BookingState.PENDING);
        bookingRepository.save(sampleBooking);

        Booking updatedBooking = bookingUpdate.cancel(sampleBookingId);

        assertEquals(BookingState.CANCELED, updatedBooking.getState());
        assertFalse(emailProviderStub.getEmails().isEmpty()); // Assuming emails are sent for canceling
    }

    @Test
    void testValidBooking() {
        Booking sampleBooking = createSampleBooking(sampleBookingId, BookingState.PENDING);
        bookingRepository.save(sampleBooking);

        Booking updatedBooking = bookingUpdate.valid(sampleBookingId);

        assertEquals(BookingState.VALIDATED, updatedBooking.getState());
        assertFalse(emailProviderStub.getEmails().isEmpty()); // Assuming emails are sent for validation
    }

    private Booking createSampleBooking(UUID id, BookingState state) {
        Address sampleAddress = new Address("123 Rue de Test", 66667, "Testland");
        Hall sampleHall = new Hall(id, "TestHall", sampleAddress, 100);
        Tenant sampleUser = new Tenant(id, "TestUser", "test@mail.com", sampleAddress, true);
        Timeslot sampleTimeslot = new Timeslot(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Payment samplePayment = new Payment(new BigDecimal("100.0"), false, PaymentType.CASH, id);
        return new Booking(id, sampleHall, sampleUser, sampleTimeslot, state, samplePayment, false, "TestUse");
    }


    public static class EmailServiceImpl implements EmailService {

        private final EmailProvider emailProvider;

        public EmailServiceImpl(EmailProvider emailProvider) {
            this.emailProvider = emailProvider;
        }

        @Override
        public void sendEmailNotifyBookingToUser(Booking booking) {
            emailProvider.sendEmail(booking.getUser().getEmail(), "Votre réservation", "Détails de votre réservation ...");
        }

        @Override
        public void sendEmailNotifyBookingToRecipient(Booking booking) {
            String recipient = emailProvider.findRecipient();
            emailProvider.sendEmail(recipient, "Notification de réservation", "Une réservation a été effectuée ...");
        }
    }
}