package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingUpdate;
import fr.hoenheimsports.bookdomain.api.EmailService;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.UUID;

@DomainService
public class BookingUpdateImpl implements BookingUpdate {
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public BookingUpdateImpl(BookingRepository bookingRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    @Override
    public Booking accept(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.ACCEPTED);
        this.emailService.sendEmailNotifyBookingToUser(booking);
        this.emailService.sendEmailNotifyBookingToRecipient(booking);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking refuse(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.REFUSED);
        this.emailService.sendEmailNotifyBookingToUser(booking);
        this.emailService.sendEmailNotifyBookingToRecipient(booking);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking cancel(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.CANCELED);
        this.emailService.sendEmailNotifyBookingToUser(booking);
        this.emailService.sendEmailNotifyBookingToRecipient(booking);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking valid(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.VALIDATED);
        this.emailService.sendEmailNotifyBookingToUser(booking);
        this.emailService.sendEmailNotifyBookingToRecipient(booking);
        return this.bookingRepository.save(booking);
    }
}
