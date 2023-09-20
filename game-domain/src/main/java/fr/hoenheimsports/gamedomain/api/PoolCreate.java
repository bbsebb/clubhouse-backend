package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.PoolAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;

public interface PoolCreate {
    Pool create(String code, String name, Competition competition)  ;
}
