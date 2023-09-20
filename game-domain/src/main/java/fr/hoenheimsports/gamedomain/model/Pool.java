package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a pool in a specific competition.
 * <p>
 * This record provides a structured way to handle and represent pools associated with a given competition.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code code} must not be {@code null}.</li>
 *   <li>{@code name} must not be {@code null}.</li>
 *   <li>{@code competition} must not be {@code null}.</li>
 * </ul>
 */
public record Pool(String code, String name,Competition competition) {
    /**
     * A constant representing an unknown pool.
     */
    public static final Pool UNKNOWN = new Pool("unknown","unknown",Competition.UNKNOWN);
    /**
     * Constructs an instance of {@code Pool} after validating its parameters.
     */
    public Pool {
        Objects.requireNonNull(code,"code should be not null");
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(competition,"competition should be not null");
    }
}
