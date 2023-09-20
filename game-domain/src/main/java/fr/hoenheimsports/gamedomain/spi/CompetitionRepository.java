package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Competition;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Competition} entities.
 * <p>
 * This repository abstracts the persistence details of the Competition entities and offers methods to access and
 * manipulate the underlying competition data based on its unique name, which serves as its primary identifier.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific competition based on its unique name.</li>
 *   <li>Persist a competition entity to the data store or update it if it already exists.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * CompetitionRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Competition> footballOpt = repository.findById("Football");
 * Competition newCompetition = new Competition("Basketball");
 * repository.save(newCompetition);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Competition
 */
public interface CompetitionRepository {

    /**
     * Persists the provided competition to the data store. If a competition with the same name already exists,
     * the existing entity is updated.
     *
     * @param competition the competition entity to be persisted or updated.
     * @return the persisted or updated competition entity.
     */
    Competition save(Competition competition);

    /**
     * Searches for a specific competition in the data store based on its unique name.
     *
     * @param name the unique name of the competition serving as its primary identifier.
     * @return an {@link Optional} that contains the matched competition if found, otherwise it's empty.
     */
    Optional<Competition> findById(String name);
}
