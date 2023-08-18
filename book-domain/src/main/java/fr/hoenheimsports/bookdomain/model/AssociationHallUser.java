package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

public record AssociationHallUser(UUID id,String username,String email) implements HallUser {
    public AssociationHallUser {
        Objects.requireNonNull(id,"id should be not null");
        Objects.requireNonNull(username,"username should be not null");
        Objects.requireNonNull(email,"email should be not null");
    }
}
