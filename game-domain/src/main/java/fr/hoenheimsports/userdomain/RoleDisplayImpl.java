package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.RoleDisplay;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.spi.RoleRepository;

import java.util.Set;
@DomainService
public class RoleDisplayImpl implements RoleDisplay {
    private final RoleRepository roleRepository;

    public RoleDisplayImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
