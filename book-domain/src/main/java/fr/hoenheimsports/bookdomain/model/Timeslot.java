package fr.hoenheimsports.bookdomain.model;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Timeslot class represents a timeslot. It contains the following attributes:
 * <ul>
 *      <li>start: the start date of the timeslot</li>
 *      <li>end: the end date of the timeslot</li>
 * </ul>
 */
public record Timeslot(LocalDateTime start, LocalDateTime end) {
    public Timeslot {
        Objects.requireNonNull(start, "start should not be null");
        Objects.requireNonNull(end, "end should not be null");
        if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("'start' should start before 'end'");
        }
    }

    /**
     * Check if the timeslot overlaps with another timeslot
     * @param other the other timeslot
     * @return true if the timeslot overlaps with the other timeslot, false otherwise
     */
    public boolean isOverlaps(Timeslot other) {
        boolean entirelyBefore = other.end.isBefore(this.start) || other.end.isEqual(this.start);
        boolean entirelyAfter = other.start.isAfter(this.end) || other.start.isEqual(this.end);
        return !(entirelyBefore || entirelyAfter);
    }

    private boolean isFollows(Timeslot other) {
        return this.end.isEqual(other.start) || this.start.equals(other.end);
    }

    /**
     * Merge the timeslot with another timeslot
     * @param overlappingTimeslot the other timeslot
     * @return the merged timeslot
     */
    public Timeslot mergeOverlappingTimeslot(Timeslot overlappingTimeslot) {
        Timeslot mergedTimeslot = this;
        if (this.isOverlaps(overlappingTimeslot) || this.isFollows(overlappingTimeslot)) {
            var earliestStart = start.isBefore(overlappingTimeslot.start) ? start : overlappingTimeslot.start;
            var latestEnd = end.isAfter(overlappingTimeslot.end) ? end : overlappingTimeslot.end;
            mergedTimeslot = new Timeslot(earliestStart, latestEnd);
        }
        return mergedTimeslot;
    }
}
