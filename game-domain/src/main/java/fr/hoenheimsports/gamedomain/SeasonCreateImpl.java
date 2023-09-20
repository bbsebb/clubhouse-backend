package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.SeasonCreate;
import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.gamedomain.spi.SeasonRepository;

import java.time.LocalDate;
@DomainService
public class SeasonCreateImpl implements SeasonCreate {
    private final SeasonRepository seasonRepository;

    public SeasonCreateImpl(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public Season create(LocalDate date) {
        Season season = this.determineSeason(date);
        return this.seasonRepository.findById(season.name())
                .orElseGet(() -> this.seasonRepository.save(season));
    }

    /**
     * Determines the game season based on the provided date. If no date is given,
     * the current season based on today's date is returned.
     *
     * @param date The reference date to determine the game season.
     * @return The game season based on the provided date or the current season.
     */
    private Season determineSeason(LocalDate date) {
        // Default start date components for a season
        final int SEASON_START_MONTH = 8;
        final int SEASON_START_DAY = 1;

        // Use today's date if none is provided
        LocalDate referenceDate = (date == null) ? LocalDate.now() : date;

        LocalDate seasonStartDate = LocalDate.of(referenceDate.getYear(), SEASON_START_MONTH, SEASON_START_DAY);
        LocalDate seasonEndDate;

        if (referenceDate.isAfter(seasonStartDate) || referenceDate.isEqual(seasonStartDate)) {
            seasonEndDate = seasonStartDate.plusYears(1).minusDays(1);
        } else {
            seasonEndDate = seasonStartDate.minusDays(1);
            seasonStartDate = seasonStartDate.minusYears(1);
        }

        String name = "SEASON_" + seasonStartDate.getYear() + "_" + seasonEndDate.getYear();
        return new Season(name, seasonStartDate, seasonEndDate);
    }
}
