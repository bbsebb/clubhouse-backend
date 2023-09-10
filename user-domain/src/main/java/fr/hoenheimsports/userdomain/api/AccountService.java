package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.Optional;

public interface AccountService {
    Optional<User> loadByUsername(String username);
    Optional<User> loadByEmail(String email);
    User loadByLogin(String login) throws UserNotFoundException;

}
