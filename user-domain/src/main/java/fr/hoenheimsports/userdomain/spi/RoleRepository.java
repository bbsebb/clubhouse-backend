package fr.hoenheimsports.userdomain.spi;

import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.model.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository {
    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findById(UUID id);

    Role save(Role role);

    Set<Role> findAll();
}
