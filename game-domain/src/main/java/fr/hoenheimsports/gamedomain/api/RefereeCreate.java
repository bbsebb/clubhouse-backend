package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.RefereeAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Referee;

public interface RefereeCreate {
    Referee create(String name)  ;
}
