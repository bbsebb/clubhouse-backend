package fr.hoenheimsports.bookdomain.api;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.HallUser;

import java.util.Optional;
import java.util.UUID;

public interface HallUserCreate {
    HallUser createUnregisteredUser(String username, String email, Address address, boolean isMembre);
    HallUser createRegisteredUser(UUID id,String username, String email);

    Optional<HallUser> findByUd(UUID id);
}
