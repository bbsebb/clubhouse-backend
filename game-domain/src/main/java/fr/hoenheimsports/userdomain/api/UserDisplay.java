package fr.hoenheimsports.userdomain.api;

import fr.hoenheimsports.userdomain.model.User;

import java.util.Set;

public interface UserDisplay {
    Set<User> findAll();

}
