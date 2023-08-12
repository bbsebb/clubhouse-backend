package fr.hoenheimsports.userdomain.spi;

import fr.hoenheimsports.userdomain.model.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
