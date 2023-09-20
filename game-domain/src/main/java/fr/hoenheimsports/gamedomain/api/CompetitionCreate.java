package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.CompetitionAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;

public interface CompetitionCreate {
    Competition create(String name) ;
}
