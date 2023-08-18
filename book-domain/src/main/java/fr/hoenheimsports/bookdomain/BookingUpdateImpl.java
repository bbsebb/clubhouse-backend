package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingUpdate;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;
import fr.hoenheimsports.bookdomain.spi.EmailProvider;
@DomainService
public class BookingUpdateImpl implements BookingUpdate {
    private final BookingRepository bookingRepository;
    private final EmailProvider emailService;


    public BookingUpdateImpl(BookingRepository bookingRepository, EmailProvider emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    @Override
    public Booking accept(Booking booking) {
        this.emailService.sendEmail(booking.user().email(),booking);
        return this.bookingRepository.save(new Booking(
                booking.id(),
                booking.hall(),
                booking.user(),
                booking.timeslot(),
                BookingState.ACCEPTED
        ));
    }

    @Override
    public Booking refuse(Booking booking) {
        this.emailService.sendEmail(booking.user().email(),booking);
        return this.bookingRepository.save(new Booking(
                booking.id(),
                booking.hall(),
                booking.user(),
                booking.timeslot(),
                BookingState.UNAUTHORIZED
        ));
    }

    @Override
    public Booking cancel(Booking booking) {
        this.emailService.sendEmail(booking.user().email(),booking);
        return this.bookingRepository.save(new Booking(
                booking.id(),
                booking.hall(),
                booking.user(),
                booking.timeslot(),
                BookingState.CANCELED
        ));
    }

    @Override
    public Booking valid(Booking booking) {
        this.emailService.sendEmail(booking.user().email(),booking);
        return this.bookingRepository.save(new Booking(
                booking.id(),
                booking.hall(),
                booking.user(),
                booking.timeslot(),
                BookingState.VALIDATED
        ));
    }
}
