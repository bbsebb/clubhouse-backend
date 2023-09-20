package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Category;
import java.util.List;
import java.util.Optional;

/**
 * Represents the data access mechanism to fetch, persist, and manipulate {@link Category} entities.
 * <p>
 * The core objective of this repository is to abstract away the persistence details and offer a set of methods
 * that can be used to access and manipulate the underlying category data.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li>Retrieve all categories from the data store.</li>
 *   <li>Find a specific category based on its unique name.</li>
 *   <li>Save a category entity to the data store.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * CategoryRepository repository = ...;  // Obtained through dependency injection or other mechanisms.
 * List<Category> allCategories = repository.findAll();
 * Optional<Category> musicCategoryOpt = repository.findById("Music");
 * Category newCategory = new Category("Sports", 25, false);
 * repository.save(newCategory);
 * }
 * </pre>
 *
 * @author YourNameHere
 * @version 1.0
 * @see Category
 */
public interface CategoryRepository {

    /**
     * Retrieves all the available categories from the data store.
     *
     * @return a list of all categories. The list might be empty if no categories are found.
     */
    List<Category> findAll();

    /**
     * Searches for a specific category based on its unique name identifier.
     *
     * @param name the unique name of the category.
     * @return an {@link Optional} containing the found category if it exists, otherwise an empty optional.
     */
    Optional<Category> findById(String name);

    /**
     * Persists the given category to the data store. If the category already exists, it will be updated.
     *
     * @param category the category entity to be persisted.
     * @return the persisted (or updated) category entity.
     */
    Category save(Category category);
}
