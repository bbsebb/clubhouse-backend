package fr.hoenheimsports.model;

import java.util.Objects;
import java.util.UUID;

public record Referee(UUID id, String name, PhoneNumber phoneNumber) implements Contributor{
    public static final Referee UNKNOWN = new Referee(UUID.fromString("00000000-0000-0000-0000-000000000000"),"unknown",new PhoneNumber("00"));
    public Referee {
        Objects.requireNonNull(id, "id should not be null");
        Objects.requireNonNull(name, "name should not be null");
        Objects.requireNonNull(phoneNumber, "phoneNumber should not be null");
    }
}
