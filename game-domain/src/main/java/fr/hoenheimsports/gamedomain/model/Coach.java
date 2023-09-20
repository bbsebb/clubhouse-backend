package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
import java.util.UUID;
/**
 * Represents a coach with a unique ID, a name, and a phone number. This record also implements the {@code Contributor} interface.
 * <p>
 * While the {@code id} field is considered as the unique identifier for this object, the {@code name} also serves as a primary key ensuring uniqueness amongst coaches.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>The {@code id} field must not be {@code null}.</li>
 *   <li>The {@code name}, acting as a primary key, must not be {@code null} and must be unique amongst coaches.</li>
 *   <li>The {@code phoneNumber} field must not be {@code null}.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * PhoneNumber coachPhone = new PhoneNumber("1234567890");
 * Coach footballCoach = new Coach(UUID.randomUUID(), "John Doe", coachPhone);
 * }
 * </pre>
 */
public record Coach(UUID id, String name, PhoneNumber phoneNumber) implements Contributor{
    /**
     * A constant representing an unknown coach.
     */
    public static final Coach UNKNOWN = new Coach(UUID.fromString("00000000-0000-0000-0000-000000000000"),"unknown",new PhoneNumber("00"));

    public Coach {
        Objects.requireNonNull(id, "id should not be null");
        Objects.requireNonNull(name, "name should not be null");
        Objects.requireNonNull(phoneNumber, "phoneNumber should not be null");
    }
}
