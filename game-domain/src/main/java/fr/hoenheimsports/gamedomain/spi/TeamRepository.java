package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.*;
import java.util.List;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Team} entities.
 * <p>
 * This repository offers an abstraction over the persistence layer for `Team` entities. It provides
 * methods to retrieve team instances based on specific criteria, such as the combination of the club,
 * gender, category, and team number, or based on a unique identifier (ID). It also supports retrieval
 * of all teams and saving/updating a team entity.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Search for a specific team based on a combination of club, gender, category, and team number.</li>
 *   <li>Retrieve all teams present in the database/system.</li>
 *   <li>Search for teams based on specific category and gender names.</li>
 *   <li>Search for a team using its unique identifier (ID).</li>
 *   <li>Save or update a specific team entity.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * TeamRepository repository = ...;  // Obtain an instance of the repository (e.g., via dependency injection).
 * Optional<Team> teamOpt = repository.findByKeys(clubInstance, Gender.MALE, categoryInstance, 1);
 * Team newTeam = new Team(UUID.randomUUID(), categoryInstance, Gender.FEMALE, 2, clubInstance, TeamsColor.BLUE, coachInstance);
 * repository.save(newTeam);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Team
 */
public interface TeamRepository {

    /**
     * Searches for a specific team based on a combination of club, gender, category, and team number.
     *
     * @param club the club to which the team belongs
     * @param gender the gender designation of the team
     * @param category the category of the team
     * @param number the team number
     * @return an {@link Optional} containing the matched team if found, otherwise it's empty.
     */
    Optional<Team> findByKeys(Club club, Gender gender, Category category, int number);

    /**
     * Retrieves all the teams present in the system.
     *
     * @return a list of all teams.
     */
    List<Team> findAll();

    /**
     * Searches for teams based on specific category and gender names.
     *
     * @param categoryName the name of the category
     * @param genderName the name of the gender
     * @return a list of matched teams based on the provided category and gender names.
     */
    List<Team> findByCategoryAndGender(String categoryName, String genderName);

    /**
     * Searches for a team using its unique identifier (ID).
     *
     * @param id the unique ID of the team
     * @return an {@link Optional} containing the matched team if found, otherwise it's empty.
     */
    Optional<Team> findById(String id);

    /**
     * Persists the provided team entity. If a team with the same ID already exists,
     * the existing entity should be updated.
     *
     * @param team the team entity to be saved or updated.
     * @return the persisted or updated team entity.
     */
    Team save(Team team);
}
