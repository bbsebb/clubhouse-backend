package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.exception.RoleNotFoundException;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.User;

import java.util.UUID;

public interface UserUpdate {
    public User addRole(UUID userId, UUID... roleId) throws UserNotFoundException, RoleNotFoundException;
}
