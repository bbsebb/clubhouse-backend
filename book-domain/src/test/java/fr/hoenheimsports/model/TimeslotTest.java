package fr.hoenheimsports.model;
import fr.hoenheimsports.bookdomain.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeslotTest {
    @Test
    void testStartNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Timeslot(null, LocalDateTime.now().plusHours(1)));

        String expectedMessage = "start should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEndNotNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Timeslot(LocalDateTime.now(), null));

        String expectedMessage = "end should not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStartBeforeEnd() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime now = LocalDateTime.now();
            new Timeslot(now, now);
        });

        String expectedMessage = "'start' should start before 'end'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testOverlaps() {
        var timeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,1,0)
        );

        // 1. When it doesn't overlap
        var noOverlap = new Timeslot(
                LocalDateTime.of(2020,1,1,2,0),
                LocalDateTime.of(2020,1,1,3,0)
        );
        assertFalse(timeslot.overlaps(noOverlap));

        // 2. When it doesn't overlap from the beginning but not from the end.
        var overlapStart = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,1,0,30)
        );
        assertTrue(timeslot.overlaps(overlapStart));

        // 3. When it doesn't overlap from the end but not from the beginning.
        var overlapEnd = new Timeslot(
                LocalDateTime.of(2020,1,1,0,30),
                LocalDateTime.of(2020,1,1,1,30)
        );
        assertTrue(timeslot.overlaps(overlapEnd));

        // 4. When it follows from the end but not from the beginning.
        var followEnd = new Timeslot(
                LocalDateTime.of(2020,1,1,1,0),
                LocalDateTime.of(2020,1,1,2,0)
        );
        assertFalse(timeslot.overlaps(followEnd));

        // 5. When it follows from the beginning but not from the end.
        var followStart = new Timeslot(
                LocalDateTime.of(2020,1,1,23,0),
                LocalDateTime.of(2020,1,2,0,10)
        );
        assertFalse(timeslot.overlaps(followStart));

        // 6. When it doesn't overlap from the beginning and from the end.
        var overlapBoth = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,2,0,30)
        );
        assertTrue(timeslot.overlaps(overlapBoth));

        // 7. When it is exactly the same.
        var same = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,1,0)
        );
        assertTrue(timeslot.overlaps(same));
    }

}