package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.RoleCreate;
import fr.hoenheimsports.userdomain.exception.RoleAlreadyExistException;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.spi.RoleRepository;

import java.util.UUID;
@DomainService
public class RoleCreateImpl implements RoleCreate {
    private final RoleRepository roleRepository;

    public RoleCreateImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(String name) throws RoleAlreadyExistException {
        if(this.roleRepository.findByRoleName(name).isPresent()) {
            throw new RoleAlreadyExistException("Role exist already");
        }
        return this.roleRepository.save(new Role(UUID.randomUUID(),name));
    }
}
