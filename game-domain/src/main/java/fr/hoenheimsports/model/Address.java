package fr.hoenheimsports.model;

import java.util.Objects;

public record Address(String street, int postalCode, String city) {

    public static final Address UNKNOWN = new Address("unknown",0,"unknown");

    public Address {
        Objects.requireNonNull(street,"street should be not null");
        Objects.requireNonNull(city,"city should be not null");
        if (postalCode < 0) {
            throw new IllegalArgumentException("postalCode must be equal or greater than 0");
        }
    }
}
