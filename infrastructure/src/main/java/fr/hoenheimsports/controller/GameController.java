package fr.hoenheimsports.controller;

import fr.hoenheimsports.model.*;
import fr.hoenheimsports.model.api.GameService;
import fr.hoenheimsports.model.builder.CompetitionBuilder;
import fr.hoenheimsports.model.builder.GameBuilder;
import fr.hoenheimsports.model.builder.RefereesBuilder;
import fr.hoenheimsports.model.builder.TeamBuilder;
import fr.hoenheimsports.model.spi.stub.GameRepositoryInMemory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private GameService gameService;

    public GameController() {
        this.gameService = new GameServiceImpl(new GameRepositoryInMemory());
    }

    @GetMapping("/create")
    public ResponseEntity<Game> createGame() {
        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder
                .withCode(UUID.randomUUID().toString())
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(this.gameService.gameCreate(game));
    }

    @GetMapping("")
    public List<Game> allGame() {
        return this.gameService.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
