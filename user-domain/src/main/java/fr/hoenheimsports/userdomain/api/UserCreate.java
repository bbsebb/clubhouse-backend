package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.model.User;

public interface UserCreate {
    public User create(String username, String password, String email)  throws UserAlreadyExistException;
}
