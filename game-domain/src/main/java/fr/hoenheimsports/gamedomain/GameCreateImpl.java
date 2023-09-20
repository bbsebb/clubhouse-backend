package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameCreate;
import fr.hoenheimsports.gamedomain.exception.GameAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@DomainService
public class GameCreateImpl implements GameCreate {


    private final GameRepository gameRepository;

    public GameCreateImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public Game create(Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameAlreadyExistsException {
        return this.create(UUID.randomUUID().toString(), pool, season, day, week, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);
    }

    @Override
    public Game create(String code, Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameAlreadyExistsException {
        if(this.gameRepository.findById(code).isPresent()) {
            throw new GameAlreadyExistsException();
        }
        return this.gameRepository.save(new Game(code, pool, season, day, week, halle, referees, homeTeam, visitingTeam, score, fdme, date, time));
    }
}
