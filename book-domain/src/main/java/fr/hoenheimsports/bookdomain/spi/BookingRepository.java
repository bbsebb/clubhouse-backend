package fr.hoenheimsports.bookdomain.spi;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    List<Booking> findAll();

    List<Booking> findByOverlappingTimeslot(Timeslot timeslot);

    Optional<Booking> findById(UUID id);

    Booking save(Booking booking);
}
