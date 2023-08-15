package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.exception.RoleAlreadyExistException;
import fr.hoenheimsports.userdomain.model.Role;

public interface RoleCreate {
    public Role create(String name) throws RoleAlreadyExistException;
}
