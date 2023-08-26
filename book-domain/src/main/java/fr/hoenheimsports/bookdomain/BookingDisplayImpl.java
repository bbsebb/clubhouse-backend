package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.annotation.DomainService;
import fr.hoenheimsports.bookdomain.api.BookingDisplay;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;
import fr.hoenheimsports.bookdomain.spi.BookingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DomainService
public class BookingDisplayImpl implements BookingDisplay {
    private final BookingRepository bookingRepository;

    public BookingDisplayImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public List<Booking> findByTimeslot(Timeslot timeslot) {
        return this.bookingRepository.findByOverlappingTimeslot(timeslot);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return this.bookingRepository.findById(id);
    }
}
