package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;

public record Address(String street, int cp, String city) {
    public Address {
        Objects.requireNonNull(street,"street should be not null");
        Objects.requireNonNull(city,"city should be not null");
        if (cp < 0) {
            throw new IllegalArgumentException("postalCode must be equal or greater than 0");
        }
    }
}
