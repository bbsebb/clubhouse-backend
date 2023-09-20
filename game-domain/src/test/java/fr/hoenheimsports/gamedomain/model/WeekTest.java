package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeekTest {
    @Test
    void testWeekValidYearAndWeekNumber() {
        Week week = new Week(2023, 12);
        assertEquals(2023, week.year());
        assertEquals(12, week.week());
    }

    @Test
    void testWeekInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> new Week(1899, 12));
    }

    @Test
    void testWeekLowInvalidWeekNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Week(2023, 0));
    }

    @Test
    void testWeekHighInvalidWeekNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Week(2023, 53));
    }

    @Test
    void testWeekFromLocalDate() {
        LocalDate date = LocalDate.of(2023, 3, 20); // This assumes it's the 12th week. Adjust as necessary based on calendar specifics.
        Week week = new Week(date);
        assertEquals(2023, week.year());
        assertEquals(12, week.week());
    }

    @Test
    void testGetWeekNumber() {
        LocalDate date = LocalDate.of(2023, 3, 20); // This assumes it's the 12th week. Adjust as necessary.
        assertEquals(12, Week.getWeekNumber(date));
    }

    @Test
    void testNowIsCurrentWeek() {
        Week currentWeek = Week.NOW;
        Week manuallyCalculatedWeek = new Week(LocalDate.now());
        assertEquals(currentWeek, manuallyCalculatedWeek);
    }

}