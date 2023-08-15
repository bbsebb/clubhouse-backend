package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.UserDisplay;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.Set;
@DomainService
public class UserDisplayImpl implements UserDisplay {
    private final UserRepository userRepository;

    public UserDisplayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        return this.userRepository.findAll();
    }
}
