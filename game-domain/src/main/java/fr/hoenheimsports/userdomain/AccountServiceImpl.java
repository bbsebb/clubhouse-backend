package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.AccountService;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.Optional;
@DomainService
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> loadByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
