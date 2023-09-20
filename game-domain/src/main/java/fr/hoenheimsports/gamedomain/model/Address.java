package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a physical address, including the street, postal code, and city.
 * <p>
 * The record provides a compact and efficient representation of an immutable address.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code street} must not be {@code null}.</li>
 *   <li>{@code city} must not be {@code null}.</li>
 *   <li>{@code postalCode} must be equal to or greater than 0.</li>
 * </ul>
 */
public record Address(String street, int postalCode, String city) {

    /**
     * A constant representing an unknown address.
     */
    public static final Address UNKNOWN = new Address("unknown",0,"unknown");
    /**
     * Constructs an instance of {@code Address} after validating the provided parameters.
     */
    public Address {
        Objects.requireNonNull(street,"street should be not null");
        Objects.requireNonNull(city,"city should be not null");
        if (postalCode < 0) {
            throw new IllegalArgumentException("postalCode must be equal or greater than 0");
        }
    }
}
