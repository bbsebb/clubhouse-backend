package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a phone number.
 * <p>
 * This record provides a structured way to handle and represent phone numbers across various systems.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code phoneNumber} must not be {@code null}.</li>
 * </ul>
 */
public record PhoneNumber(String phoneNumber) {
    /**
     * A constant representing an unknown phone number.
     */
    public static final PhoneNumber UNKNOWN = new PhoneNumber("unknown");
    /**
     * Constructs an instance of {@code PhoneNumber} after validating the provided phone number string.
     */
    public PhoneNumber {
        Objects.requireNonNull(phoneNumber, "phoneNumber should not be null");
    }
}
