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
    public boolean isOverlaps(Timeslot other) {
        boolean entirelyBefore = other.end.isBefore(this.start) || other.end.isEqual(this.start);
        boolean entirelyAfter = other.start.isAfter(this.end) || other.start.isEqual(this.end);
        return !(entirelyBefore || entirelyAfter);
    }

    private boolean isFollows(Timeslot other) {
        return this.end.isEqual(other.start) || this.start.equals(other.end);
    }

    public Timeslot mergeOverlappingTimeslot(Timeslot overlappingTimeslot) {
        Timeslot mergedTimeslot = this;
        if(this.isOverlaps(overlappingTimeslot) || this.isFollows(overlappingTimeslot)) {
            var earliestStart = start.isBefore(overlappingTimeslot.start) ? start : overlappingTimeslot.start;
            var latestEnd = end.isAfter(overlappingTimeslot.end) ? end : overlappingTimeslot.end;
            mergedTimeslot = new Timeslot(earliestStart,latestEnd);
        }
        return mergedTimeslot;
    }
}
