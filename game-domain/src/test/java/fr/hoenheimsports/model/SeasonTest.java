package fr.hoenheimsports.model;

import fr.hoenheimsports.model.Season;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {
    @Test
    public void testConstructor_WithNullArguments_ThrowsNullPointerException() {
        String name = null;
        LocalDate startDate = null;
        LocalDate endDate = null;

        assertThrows(NullPointerException.class, () -> {
            new Season(name, startDate, endDate);
        });
    }

    @Test
    public void testConstructor_ValidArguments_Successful() {
        String name = "SEASON_2022_2023";
        LocalDate startDate = LocalDate.of(2022, 8, 1);
        LocalDate endDate = LocalDate.of(2023, 7, 31);

        Season season = new Season(name, startDate, endDate);

        assertEquals(name, season.name());
        assertEquals(startDate, season.startDate());
        assertEquals(endDate, season.endDate());
    }
}