package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

public record Tenant(UUID id,String username,String email,Address address) implements HallUser {

    public Tenant {
        Objects.requireNonNull(id,"id should be not null");
        Objects.requireNonNull(username,"username should be not null");
        Objects.requireNonNull(email,"email should be not null");
        Objects.requireNonNull(address,"address should be not null");
    }
}
