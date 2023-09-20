package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.ClubAlreadyExistsException;
import fr.hoenheimsports.gamedomain.exception.ClubNotFoundException;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;

import java.util.Set;

public interface ClubUpdate {
    Club update(String code, String name, Set<Hall> halles) throws ClubNotFoundException;
    Club update(String code, String name,Hall... halles) throws ClubNotFoundException;
}
