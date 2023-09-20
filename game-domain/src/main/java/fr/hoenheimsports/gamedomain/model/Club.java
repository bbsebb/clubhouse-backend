package fr.hoenheimsports.gamedomain.model;

import java.util.*;
/**
 * Represents a club with a unique code, a name, and a set of halls.
 * <p>
 * The {@code code} field is considered as the unique identifier for this object.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>The {@code code} field must not be {@code null}.</li>
 *   <li>The {@code name} field must not be {@code null}.</li>
 *   <li>The {@code halls} field must not be {@code null} and its contents are copied to ensure immutability.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * Set<Hall> hallsSet = Set.of(new Hall(...));
 * Club sportsClub = new Club("code123", "Sports Club", hallsSet);
 * }
 * </pre>
 */
public record Club(String code, String name, Set<Hall> halls) {
    /**
     * A constant representing an unknown club.
     */
    public static final Club UNKNOWN = new Club(UUID.fromString("00000000-0000-0000-0000-000000000000").toString(),"unknown", Set.of(Hall.UNKNOWN));
    public Club {
        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(name, "name should not be null");
        Objects.requireNonNull(halls,"halls should not be null");
        halls = Set.copyOf(halls);
    }
}
