package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a category with a unique name, age, and a flag indicating if it's a max age.
 * <p>
 * The {@code name} field is considered as a unique identifier for this object.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>The {@code name} field must not be {@code null}.</li>
 *   <li>The {@code age} field should be greater than or equal to 0.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * Category musicCategory = new Category("Music", 21, false);
 * }
 * </pre>
 */
public record Category(String name,int age, boolean isMaxAge) {
    /**
     * A constant representing an unknown category.
     */
    public static final Category UNKNOWN = new Category("unknown",0,true);
    public Category {
        Objects.requireNonNull(name, "name should not be null");
        if(age<0) {
            throw new IllegalArgumentException("age shoud be greater than 0");
        }
    }
}


