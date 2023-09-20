package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.SeasonAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Season;

import java.time.LocalDate;

public interface SeasonCreate {
    Season create(LocalDate date) ;
}
