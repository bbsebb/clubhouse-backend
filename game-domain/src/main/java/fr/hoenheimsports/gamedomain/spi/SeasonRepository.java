package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Season;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Season} entities.
 * <p>
 * This repository offers an abstraction over the persistence layer for `Season` entities. It provides
 * methods to retrieve season instances based on specific criteria, like the date a game occurs or
 * the unique identifier (name) of a season.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific season where a particular date falls.</li>
 *   <li>Search for a specific season using its unique identifier (name).</li>
 *   <li>Save or update a specific season entity.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * SeasonRepository repository = ...;  // Obtain an instance of the repository (e.g., via dependency injection).
 * Optional<Season> seasonOpt = repository.findByDate(LocalDate.of(2022, 12, 15));
 * Season newSeason = new Season("SEASON_2023_2024", LocalDate.of(2023, 8, 1), LocalDate.of(2024, 7, 31));
 * repository.save(newSeason);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Season
 */
public interface SeasonRepository {

    /**
     * Retrieves a specific season based on a given date.
     *
     * @param localDate the specific date to search for.
     * @return an {@link Optional} containing the matched season if found, otherwise it's empty.
     */
    Optional<Season> findByDate(LocalDate localDate);

    /**
     * Retrieves a season using its unique identifier, which is its name.
     *
     * @param name the unique name (identifier) of the season.
     * @return an {@link Optional} containing the matched season if found, otherwise it's empty.
     */
    Optional<Season> findById(String name);

    /**
     * Persists the provided season entity. If a season with the same unique identifier (name) already exists,
     * the existing entity should be updated.
     *
     * @param season the season entity to be saved or updated.
     * @return the persisted or updated season entity.
     */
    Season save(Season season);
}
