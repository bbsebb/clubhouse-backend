package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameUpdate;
import fr.hoenheimsports.gamedomain.exception.GameNotFoundException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@DomainService
public class GameUpdateImpl implements GameUpdate {

    private final GameRepository gameRepository;

    public GameUpdateImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game updateGame(String code, Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) throws GameNotFoundException {
        Optional<Game> game = this.gameRepository.findById(code);
        if(game.isEmpty()) {
            throw new GameNotFoundException();
        }
        return this.gameRepository.save(new Game(code, pool, season, day, week, halle, referees, homeTeam, visitingTeam, score, fdme, date, time));
    }
}
