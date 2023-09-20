package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.gamedomain.spi.SeasonRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Stub
public class SeasonRepositoryStub implements SeasonRepository {
    private final Map<String, Season> seasons = new HashMap<>();
    @Override
    public Optional<Season> findByDate(LocalDate localDate) {
        return this.seasons.values().stream()
                .filter(season -> season.startDate().isBefore(localDate) && season.endDate().isAfter(localDate))
                .findFirst();
    }

    @Override
    public Optional<Season> findById(String name) {
        return Optional.ofNullable(this.seasons.get(name));
    }

    @Override
    public Season save(Season season) {
        this.seasons.put(season.name(),season);
        return season;
    }
}
