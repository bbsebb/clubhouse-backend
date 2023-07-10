package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Season;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SeasonBuilderTest {
    @Test
    public void testSeasonBuilder() {
        String name = "2023";
        LocalDate startDate = LocalDate.of(2023, 3, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);

        Season season = SeasonBuilder.builder()
                .withName(name)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .build();

        assertNotNull(season, "Season is null");
        assertEquals(name, season.name(), "Name is incorrect");
        assertEquals(startDate, season.startDate(), "Start date is incorrect");
        assertEquals(endDate, season.endDate(), "End date is incorrect");
    }
}