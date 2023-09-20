package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeasonTest {
    @Test
    public void testConstructorValidInput() {
        Season season = new Season("SEASON_2024_2025", LocalDate.of(2024, 8, 1), LocalDate.of(2025, 7, 31));

        assertEquals("SEASON_2024_2025", season.name());
        assertEquals(LocalDate.of(2024, 8, 1), season.startDate());
        assertEquals(LocalDate.of(2025, 7, 31), season.endDate());
    }

    @Test
    public void testConstructorNullName() {
        assertThrows(NullPointerException.class, () -> new Season(null, LocalDate.of(2024, 8, 1), LocalDate.of(2025, 7, 31)));
    }

    @Test
    public void testConstructorNullStartDate() {
        assertThrows(NullPointerException.class, () -> new Season("SEASON_2024_2025", null, LocalDate.of(2025, 7, 31)));
    }

    @Test
    public void testConstructorNullEndDate() {
        assertThrows(NullPointerException.class, () -> new Season("SEASON_2024_2025", LocalDate.of(2024, 8, 1), null));
    }

    @Test
    public void testSeason2022_2023Constant() {
        Season season2022_2023 = Season.SEASON_2022_2023;

        assertEquals("SEASON_2022_2023", season2022_2023.name());
        assertEquals(LocalDate.of(2022, 8, 1), season2022_2023.startDate());
        assertEquals(LocalDate.of(2023, 7, 31), season2022_2023.endDate());
    }

}