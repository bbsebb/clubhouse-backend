package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
import java.util.UUID;

public record PhoneNumber(String phoneNumber) {
    public static final PhoneNumber UNKNOWN = new PhoneNumber("unknown");

    public PhoneNumber {
        Objects.requireNonNull(phoneNumber, "phoneNumber should not be null");
    }
}
