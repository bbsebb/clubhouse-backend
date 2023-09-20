package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
import java.util.UUID;
/**
 * Represents a referee, typically in the context of sports competitions.
 * <p>
 * This record provides essential details about a referee, including a unique identifier and a name.
 * Referees play a vital role in ensuring fair play and enforcing game rules during competitions.
 * </p>
 * <p>
 * The {@code name} field acts as a primary key and also corresponds to the {@code id} field,
 * ensuring that each referee has a unique name and identifier.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code id} serves as the unique identifier for the referee and must not be {@code null}.</li>
 *   <li>{@code name} represents the full name of the referee and must not be {@code null}.</li>
 * </ul>
 *
 * <h2>Implements:</h2>
 * <ul>
 *   <li>{@code Contributor} - indicating that the referee can be a contributor in various contexts.</li>
 * </ul>
 */
public record Referee(UUID id, String name) implements Contributor{
    /**
     * A constant representing an unknown referee.
     */
    public static final Referee UNKNOWN = new Referee(UUID.fromString("00000000-0000-0000-0000-000000000000"),"unknown");
    /**
     * Constructs an instance of {@code Referee} after validating the provided ID and name.
     */
    public Referee {
        Objects.requireNonNull(id, "id should not be null");
        Objects.requireNonNull(name, "name should not be null");
    }
}
