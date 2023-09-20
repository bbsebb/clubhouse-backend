package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.GameNotFoundException;
import fr.hoenheimsports.gamedomain.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

public interface GameUpdate {
    Game updateGame(String code, Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameNotFoundException;
}
