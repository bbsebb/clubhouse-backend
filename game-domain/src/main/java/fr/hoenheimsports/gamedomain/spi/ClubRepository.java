package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Club;
import java.util.List;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Club} entities.
 * <p>
 * This repository abstracts the persistence details of the Club entities, offering methods to access and manipulate
 * the underlying club data. It enables functionalities like fetching all clubs, searching for a club by its unique code,
 * and persisting a club entity.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Retrieve all clubs from the data store.</li>
 *   <li>Search for a specific club based on its unique code identifier.</li>
 *   <li>Persist a club entity to the data store or update it if it already exists.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * ClubRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * List<Club> allClubs = repository.findAll();
 * Optional<Club> sportsClubOpt = repository.findById("code123");
 * Club newClub = new Club("code456", "Another Club", Set.of(new Hall(...)));
 * repository.save(newClub);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Club
 */
public interface ClubRepository {

    /**
     * Fetches all available clubs from the data store.
     *
     * @return a list containing all clubs. If no clubs are present, an empty list is returned.
     */
    List<Club> findAll();

    /**
     * Searches for a specific club in the data store based on its unique code identifier.
     *
     * @param code the unique code of the club.
     * @return an {@link Optional} that contains the matched club if found, otherwise it's empty.
     */
    Optional<Club> findById(String code);

    /**
     * Persists the provided club to the data store. If a club with the same code already exists,
     * the existing entity is updated.
     *
     * @param club the club entity to be persisted or updated.
     * @return the persisted or updated club entity.
     */
    Club save(Club club);
}
