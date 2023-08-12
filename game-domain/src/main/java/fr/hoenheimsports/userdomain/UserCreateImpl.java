package fr.hoenheimsports.userdomain;

import fr.hoenheimsports.userdomain.api.UserCreate;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;

import java.util.UUID;

public class UserCreateImpl implements UserCreate {
    private final UserRepository userRepository;

    public UserCreateImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String password, String email) throws UserAlreadyExistException {

        if(this.userRepository.findByUsername(username).isPresent() || this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User exist already");
        }
        return  new User(UUID.randomUUID(),username,password,email,null);
    }
}
