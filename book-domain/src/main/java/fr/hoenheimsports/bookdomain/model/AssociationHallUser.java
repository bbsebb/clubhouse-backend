package fr.hoenheimsports.bookdomain.model;

import java.util.UUID;

public class AssociationHallUser extends HallUser {
    public AssociationHallUser(UUID id, String username, String email) {
        super(id,username,email);
    }
}
