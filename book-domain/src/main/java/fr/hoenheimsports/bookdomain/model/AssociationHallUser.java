package fr.hoenheimsports.bookdomain.model;

import java.util.UUID;

/**
 * AssociationHallUser class is a subclass of HallUser. It represents an association user of the application.
 */
public class AssociationHallUser extends HallUser {
    public AssociationHallUser(UUID id, String username, String email) {
        super(id, username, email);
    }
}
