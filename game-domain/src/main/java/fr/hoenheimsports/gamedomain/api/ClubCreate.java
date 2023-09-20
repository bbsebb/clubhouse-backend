package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.ClubAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;

import java.util.Set;

public interface ClubCreate {

    Club create(String code, String name, Set<Hall> halles) throws ClubAlreadyExistsException;
    Club create(String code, String name,Hall... halles) throws ClubAlreadyExistsException;
}
