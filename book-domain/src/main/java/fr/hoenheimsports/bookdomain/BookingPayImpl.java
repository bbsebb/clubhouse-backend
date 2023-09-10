package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingPay;
import fr.hoenheimsports.bookdomain.api.EmailService;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.model.PaymentType;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.UUID;

@DomainService
public class BookingPayImpl implements BookingPay {
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public BookingPayImpl(BookingRepository bookingRepository, EmailService emailProvider) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailProvider;
    }

    @Override
    public Booking pay(UUID id, PaymentType paymentType, UUID collectorId) {
        Booking booking = this.bookingRepository.findById(id).orElseThrow();
        booking.pay(paymentType, collectorId);
        booking.setState(BookingState.VALIDATED);
        booking = this.bookingRepository.save(booking);
        this.emailService.sendEmailNotifyBookingToUser(booking);
        this.emailService.sendEmailNotifyBookingToRecipient(booking);
        return booking;
    }
}
