package fr.hoenheimsports.bookdomain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Timeslot(LocalDateTime start,LocalDateTime end) {
    public Timeslot {
        Objects.requireNonNull(start,"start should not be null");
        Objects.requireNonNull(end,"end should not be null");
        if(start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("'start' should start before 'end'");
        }
    }
    public boolean overlaps(Timeslot other) {
        return !(this.start.isAfter(other.end) ||
                this.end.isBefore(other.start) ||
                this.end.isEqual(other.start) ||
                this.start.isEqual(other.end));
    }
}
