package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;
import java.util.List;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Game} entities.
 * <p>
 * This repository abstracts the persistence details of the Game entities and provides methods to access and
 * manipulate the underlying game data based on its unique code.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Persist a new game entity or update an existing one based on its unique code.</li>
 *   <li>Search for a specific game using its unique code.</li>
 *   <li>Fetch all available game entities.</li>
 *   <li>Update the properties of an existing game.</li>
 *   <li>Delete a specific game using its unique code.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * GameRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Game> gameOpt = repository.findById("GAME_CODE_123");
 * List<Game> allGames = repository.findAll();
 * Game newGame = new Game(...);
 * repository.save(newGame);
 * repository.delete("GAME_CODE_123");
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Game
 */
public interface GameRepository {

    /**
     * Persists the provided game entity to the data store. If a game with the same unique code already exists,
     * the existing entity is updated.
     *
     * @param game the game entity to be persisted.
     * @return the persisted or updated game entity.
     */
    Game save(Game game);

    /**
     * Searches for a specific game in the data store based on its unique code.
     *
     * @param gameCode the unique code of the game.
     * @return an {@link Optional} that contains the matched game if found, otherwise it's empty.
     */
    Optional<Game> findById(String gameCode);

    /**
     * Retrieves all games available in the data store.
     *
     * @return a list containing all persisted game entities.
     */
    List<Game> findAll();

    /**
     * Updates the properties of the provided game entity in the data store.
     *
     * @param game the game entity with updated properties.
     */
    void update(Game game);

    /**
     * Deletes the specified game from the data store using its unique code.
     *
     * @param gameCode the unique code of the game to be deleted.
     */
    void delete(String gameCode);
}
