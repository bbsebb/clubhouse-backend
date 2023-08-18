package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

public record Booking(UUID id, Hall hall, HallUser user, Timeslot timeslot, BookingState state) {

    public Booking {
        Objects.requireNonNull(id,"stard should not be null");
        Objects.requireNonNull(hall,"hall should not be null");
        Objects.requireNonNull(user,"user should not be null");
        Objects.requireNonNull(timeslot,"timeslot should not be null");
        Objects.requireNonNull(state,"state should not be null");
    }
}
