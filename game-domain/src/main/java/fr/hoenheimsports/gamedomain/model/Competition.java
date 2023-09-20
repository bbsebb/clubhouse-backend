package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a competition characterized by its unique name
 * <p>
 * The {@code name} field is considered as the unique identifier for this object.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code name} must not be {@code null} and serves as a unique identifier for the competition.</li>
 * </ul>
 */
public record Competition(String name) {
    /**
     * A constant representing an unknown competition.
     */
    public static final Competition UNKNOWN = new Competition("unknown");

    /**
     * Constructs an instance of {@code Competition} after validating the provided parameters.
     */
    public Competition {
        Objects.requireNonNull(name,"name should be not null");
    }
}
