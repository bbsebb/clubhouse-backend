package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.Optional;

public interface AccountService {
    public Optional<User> loadByUsername(String username);

}
