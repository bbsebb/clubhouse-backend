package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDisplay {
    /**
     * Find all bookings
     *
     * @return List of bookings
     */
    List<Booking> findAll();

    /**
     * Find all bookings by timeslot
     * @param timeslot
     * @return List of bookings
     */
    List<Booking> findByTimeslot(Timeslot timeslot);

    /**
     * Find all bookings by user
     * @param id
     * @return List of bookings
     */
    Optional<Booking> findById(UUID id);
}
