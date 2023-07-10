package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {
    @Test
    public void testWeekCreation() {
        Week week = new Week(2023, 25);
        assertEquals(2023, week.year());
        assertEquals(25, week.week());
    }

    @Test
    public void testWeekCreationWithLocalDate() {
        LocalDate localDate = LocalDate.of(2023, 6, 20);
        Week week = new Week(localDate);
        assertEquals(2023, week.year());
        assertEquals(localDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()), week.week());
    }

    @Test
    public void testWeekCreationWithInvalidYear() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Week(1899, 25);
        });

        String expectedMessage = "year shoud be recent";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testWeekCreationWithInvalidWeek() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Week(2023, 53);
        });

        String expectedMessage = "week shoud be between 1 and 52";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testWeekCreationWithLowerBoundWeek() {
        Week week = new Week(2023, 1);
        assertEquals(2023, week.year());
        assertEquals(1, week.week());
    }

    @Test
    public void testWeekCreationWithUpperBoundWeek() {
        Week week = new Week(2023, 52);
        assertEquals(2023, week.year());
        assertEquals(52, week.week());
    }

    @Test
    public void testWeekCreationWithZeroWeek() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Week(2023, 0);
        });

        String expectedMessage = "week shoud be between 1 and 52";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}