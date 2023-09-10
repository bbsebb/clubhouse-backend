package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.annotation.DomainService;
import fr.hoenheimsports.userdomain.api.AccountService;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
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

    @Override
    public Optional<User> loadByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User loadByLogin(String login) throws  UserNotFoundException{
        Optional<User> userOpt = this.userRepository.findByUsername(login);
        if(userOpt.isEmpty()) {
            userOpt = this.userRepository.findByEmail(login);
        }
        return userOpt.orElseThrow(UserNotFoundException::new);
    }
}
