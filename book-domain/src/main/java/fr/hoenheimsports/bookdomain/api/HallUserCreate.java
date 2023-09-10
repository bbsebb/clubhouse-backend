package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.HallUser;

import java.util.Optional;
import java.util.UUID;

public interface HallUserCreate {
    /**
     * Create an unregistered user
     * @param username the username of the user
     * @param email the email of the user
     * @param address the address of the user
     * @param isMembre if the user is a member
     * @return the created user
     */
    HallUser createUnregisteredUser(String username, String email, Address address, boolean isMembre);

    /**
     * Create a registered user
     * @param id the id of the user
     * @param username the username of the user
     * @param email the email of the user
     * @return the created user
     */
    HallUser createRegisteredUser(UUID id, String username, String email);

    /**
     * Find a user by its id
     * @param id the id of the user
     * @return the user
     */
    Optional<HallUser> findByUd(UUID id);
}
