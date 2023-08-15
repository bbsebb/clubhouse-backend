package fr.hoenheimsports.bookdomain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Timeslot(LocalDateTime start,LocalDateTime end) {
    public Timeslot {
        Objects.requireNonNull(start,"stard should not be null");
        Objects.requireNonNull(end,"stard should not be null");
        if(start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("'start' should start before 'end'");
        }
    }
}
