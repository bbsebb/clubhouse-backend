package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.RoleCreate;
import fr.hoenheimsports.userdomain.api.UserCreate;
import fr.hoenheimsports.userdomain.exception.RoleAlreadyExistException;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.RoleRepository;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.Set;
import java.util.UUID;
@DomainService
public class UserCreateImpl implements UserCreate {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleCreate roleCreate;

    public UserCreateImpl(UserRepository userRepository, RoleRepository roleRepository, RoleCreate userCreate) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleCreate = userCreate;
    }

    @Override
    public User create(String username, String password, String email) throws UserAlreadyExistException {
        if(this.userRepository.findByUsername(username).isPresent() || this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User exist already");
        }
        Role defaultRole = null;
        try {
            defaultRole = this.roleCreate.create("USER");
        } catch (RoleAlreadyExistException e) {
            defaultRole = this.roleRepository.findByRoleName("USER").orElseThrow();
        }
        return  this.userRepository.save(new User(UUID.randomUUID(),username,password,email, Set.of(defaultRole)));
    }
}
