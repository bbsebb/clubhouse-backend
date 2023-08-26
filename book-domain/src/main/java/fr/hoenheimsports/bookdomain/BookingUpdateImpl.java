package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingUpdate;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.UUID;

@DomainService
public class BookingUpdateImpl implements BookingUpdate {
    private final BookingRepository bookingRepository;

    public BookingUpdateImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking accept(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.ACCEPTED);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking refuse(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.REFUSED);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking cancel(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.CANCELED);
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking valid(UUID bookingID) {
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow();
        booking.setState(BookingState.VALIDATED);
        return this.bookingRepository.save(booking);
    }
}
