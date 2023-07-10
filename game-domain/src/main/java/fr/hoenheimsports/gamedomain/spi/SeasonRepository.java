package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Season;

import java.time.LocalDate;
import java.util.Optional;

public interface SeasonRepository {
    Optional<Season> findByDate(LocalDate localDate);
}
