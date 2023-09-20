package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
import java.util.UUID;
/**
 * Represents a hall with a unique ID, a name, an address, and an associated glue authorization.
 * <p>
 * The {@code id} field is considered as the unique identifier for this object.
 * </p>
 * <p>
 * The combination of {@code name} and {@code address} fields act as a composite key such that
 * only one correspondence exists between the {@code id} and the combination of {@code name} and {@code address}.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>The {@code id} field must not be {@code null}.</li>
 *   <li>The {@code name} field must not be {@code null}.</li>
 *   <li>The {@code address} field must not be {@code null}.</li>
 *   <li>The {@code glueAuthorization} field must not be {@code null}.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * Address hallAddress = new Address(...);
 * GlueAuthorization hallAuth = new GlueAuthorization(...);
 * Hall concertHall = new Hall(UUID.randomUUID(), "Hall", hallAddress, hallAuth);
 * }
 * </pre>
 */
public record Hall(UUID id, String name, Address address, GlueAuthorization glueAuthorization) {
    /**
     * A constant representing an unknown hall.
     */
    public static final Hall UNKNOWN = new Hall(UUID.fromString("00000000-0000-0000-0000-000000000000"),"unknown",Address.UNKNOWN,GlueAuthorization.UNKNOWN);
    public Hall {
        Objects.requireNonNull(id,"id should be not null");
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(address,"address should be not null");
        Objects.requireNonNull(glueAuthorization,"glueAuthorization should be not null");
    }
}
