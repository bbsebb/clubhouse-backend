package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.UserUpdate;
import fr.hoenheimsports.userdomain.exception.RoleNotFoundException;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.RoleRepository;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@DomainService
public class UserUpdateImpl implements UserUpdate{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserUpdateImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User addRole(UUID userId, UUID... rolesId) throws UserNotFoundException, RoleNotFoundException {
        Set<Role> rolesToAdd = new HashSet<>();
        for(UUID roleId: rolesId) {
            rolesToAdd.add(this.roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new));
        }
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.getRoles().addAll(rolesToAdd);
        return this.userRepository.save(user);
    }
}
