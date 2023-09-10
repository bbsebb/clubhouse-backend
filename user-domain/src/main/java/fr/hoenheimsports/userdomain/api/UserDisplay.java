package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.model.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserDisplay {
    Set<User> findAll();
    Optional<User> findById(UUID id);

}
