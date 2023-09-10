package fr.hoenheimsports.bookdomain.model;


import java.util.Objects;
import java.util.UUID;

/**
 * Hall class is the main class of the domain. It represents a hall. It contains the following attributes:
 * @param id
 * @param name
 * @param address
 * @param capacity
 */
public record Hall(UUID id, String name, Address address, int capacity) {
    public Hall {
        Objects.requireNonNull(id, "start should not be null");
        Objects.requireNonNull(name, "name should not be null");
        Objects.requireNonNull(address, "address should not be null");
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity should be great than 0");
        }
    }
}
