package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.GameAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public interface GameCreate {

    Game create(Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameAlreadyExistsException;

    Game create(String code, Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameAlreadyExistsException;
}
