package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Pool;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Pool} entities.
 * <p>
 * This repository abstracts the persistence details of the Pool entities and provides methods to access and
 * manipulate the underlying pool data based on its unique code.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Save or update a specific pool entity based on its unique code.</li>
 *   <li>Search for a specific pool using its unique code.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * PoolRepository repository = ...;  // Instance of the repository (could be obtained via dependency injection, etc.)
 * Optional<Pool> poolOpt = repository.findById("SOME_UNIQUE_CODE");
 * Pool newPool = new Pool("NEW_CODE", "Pool Name", new Competition(...));
 * repository.save(newPool);
 * }
 * </pre>
 *
 * @author Burckhardt Sébastien
 * @version 1.0
 * @see Pool
 */
public interface PoolRepository {

    /**
     * Persists the provided pool entity to the data store. If a pool with the same unique code already exists,
     * the existing entity is updated.
     *
     * @param pool the pool entity to be persisted.
     * @return the persisted or updated pool entity.
     */
    Pool save(Pool pool);

    /**
     * Searches for a specific pool in the data store based on its unique code.
     *
     * @param code the unique code of the pool.
     * @return an {@link Optional} that contains the matched pool if found, otherwise it's empty.
     */
    Optional<Pool> findById(String code);
}
