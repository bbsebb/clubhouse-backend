package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Coach;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Coach} entities.
 * <p>
 * This repository abstracts the persistence details of the Coach entities. It offers methods to access and manipulate
 * the underlying coach data based on both the unique identifier (`id`) and the unique name, which also serves as a primary key.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific coach based on its unique name.</li>
 *   <li>Search for a specific coach based on its unique UUID identifier.</li>
 *   <li>Persist a coach entity to the data store or update it if it already exists.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * CoachRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Coach> johnDoeOpt = repository.findByKeys("John Doe");
 * Optional<Coach> specificCoachOpt = repository.findById(UUID.fromString("some-uuid-string"));
 * Coach newCoach = new Coach(UUID.randomUUID(), "Jane Smith", new PhoneNumber("0987654321"));
 * repository.save(newCoach);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Coach
 */
public interface CoachRepository {

    /**
     * Searches for a specific coach in the data store based on its unique name.
     *
     * @param name the unique name of the coach, also serving as a primary key.
     * @return an {@link Optional} that contains the matched coach if found, otherwise it's empty.
     */
    Optional<Coach> findByKeys(String name);

    /**
     * Searches for a specific coach in the data store based on its unique UUID identifier.
     *
     * @param id the UUID of the coach.
     * @return an {@link Optional} that contains the matched coach if found, otherwise it's empty.
     */
    Optional<Coach> findById(UUID id);

    /**
     * Persists the provided coach to the data store. If a coach with the same name or ID already exists,
     * the existing entity is updated.
     *
     * @param coach the coach entity to be persisted or updated.
     * @return the persisted or updated coach entity.
     */
    Coach save(Coach coach);
}
