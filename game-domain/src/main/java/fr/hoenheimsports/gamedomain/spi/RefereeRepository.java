package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Referee;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Referee} entities.
 * <p>
 * This repository provides an abstraction over the persistence details of the Referee entities and offers
 * methods to interact with the underlying data based on the referee's unique name or ID.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific referee using their unique name.</li>
 *   <li>Search for a specific referee using their unique ID.</li>
 *   <li>Save or update a specific referee entity.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * RefereeRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Referee> refereeOpt = repository.findByKeys("John Doe");
 * Referee newReferee = new Referee(UUID.randomUUID(), "Jane Smith");
 * repository.save(newReferee);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Referee
 */
public interface RefereeRepository {

    /**
     * Searches for a specific referee based on their unique name.
     *
     * @param name the unique name of the referee.
     * @return an {@link Optional} that contains the matched referee if found, otherwise it's empty.
     */
    Optional<Referee> findByKeys(String name);

    /**
     * Searches for a specific referee in the data store based on their unique ID.
     *
     * @param id the unique ID of the referee.
     * @return an {@link Optional} that contains the matched referee if found, otherwise it's empty.
     */
    Optional<Referee> findById(UUID id);

    /**
     * Persists the provided referee entity to the data store. If a referee with the same unique ID or name already
     * exists, the existing entity should be updated.
     *
     * @param referee the referee entity to be persisted.
     * @return the persisted or updated referee entity.
     */
    Referee save(Referee referee);
}
