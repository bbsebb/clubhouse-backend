package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.HallUser;

import java.util.Optional;
import java.util.UUID;

public interface HallUserDisplay {
    /**
     * Find a user by its id
     * @param id the id of the user
     * @return the user
     */
    Optional<HallUser> findById(UUID id);
}
