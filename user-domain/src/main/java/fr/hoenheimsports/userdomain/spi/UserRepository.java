package fr.hoenheimsports.userdomain.spi;

import fr.hoenheimsports.userdomain.model.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);

    public User save(User user);

    public Optional<User> findById(UUID userId);

    Set<User> findAll();
}
