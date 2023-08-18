package fr.hoenheimsports.bookdomain.model;



import java.util.Objects;
import java.util.UUID;

public record Hall(UUID id, String name, Address address, int capacity) {
    public Hall {
        Objects.requireNonNull(id,"stard should not be null");
        Objects.requireNonNull(name,"name should not be null");
        Objects.requireNonNull(address,"address should not be null");
        if(capacity<0) {
            throw new IllegalArgumentException("capacity should be great than 0");
        }
    }
}
