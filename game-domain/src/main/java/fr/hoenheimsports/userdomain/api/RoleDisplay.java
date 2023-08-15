package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.spi.RoleRepository;

import java.util.Set;

public interface RoleDisplay {
    Set<Role> findAll();
}
