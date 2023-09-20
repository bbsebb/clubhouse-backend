package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Hall;
import java.util.Optional;
import java.util.Set;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Hall} entities.
 * <p>
 * This repository abstracts the persistence details of the Hall entities and provides methods to access and
 * manipulate the underlying hall data based on its unique ID and other attributes.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific hall using its composite keys: name, address, postal code, and city.</li>
 *   <li>Search for a specific hall using its unique ID.</li>
 *   <li>Fetch all available hall entities.</li>
 *   <li>Fetch all hall entities associated with a specific club ID.</li>
 *   <li>Persist a new hall entity or update an existing one based on its unique ID.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * HallRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Hall> hallOpt = repository.findById("SOME_UNIQUE_ID");
 * Set<Hall> allHalls = repository.findAll();
 * Hall newHall = new Hall(...);
 * repository.save(newHall);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Hall
 */
public interface HallRepository {

    /**
     * Searches for a specific hall in the data store based on its composite keys: name, address, postal code, and city.
     *
     * @param name the name of the hall.
     * @param address the address of the hall.
     * @param cp the postal code of the hall's location.
     * @param city the city of the hall's location.
     * @return an {@link Optional} that contains the matched hall if found, otherwise it's empty.
     */
    Optional<Hall> findByKeys(String name, String address, int cp, String city);

    /**
     * Searches for a specific hall in the data store based on its unique ID.
     *
     * @param id the unique ID of the hall.
     * @return an {@link Optional} that contains the matched hall if found, otherwise it's empty.
     */
    Optional<Hall> findById(String id);

    /**
     * Retrieves all halls available in the data store.
     *
     * @return a set containing all persisted hall entities.
     */
    Set<Hall> findAll();

    /**
     * Retrieves all hall entities associated with a specific club ID.
     *
     * @param clubId the unique ID of the club.
     * @return a set of halls associated with the specified club ID.
     */
    Set<Hall> findByClubId(String clubId);

    /**
     * Persists the provided hall entity to the data store. If a hall with the same unique ID already exists,
     * the existing entity is updated.
     *
     * @param hall the hall entity to be persisted.
     * @return the persisted or updated hall entity.
     */
    Hall save(Hall hall);
}
