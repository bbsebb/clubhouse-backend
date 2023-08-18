package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Timeslot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDisplay {
    List<Booking> findAll();
    List<Booking> findByTimeslot(Timeslot timeslot);
    Optional<Booking> findById(UUID id);
}
