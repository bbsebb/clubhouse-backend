package fr.hoenheimsports.controller;

import fr.hoenheimsports.gamedomain.CreateGameImpl;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import fr.hoenheimsports.gamedomain.spi.stub.ImportCSVGameStub;
import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.spi.ImportFileGame;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final CreateGame gameService;
    private final ImportFileGame importCSVGame;
    private final GameRepository gameRepository;
    private final ImportFileGame importFileGame;

    public GameController(ImportFileGame importFileGame) {
        this.importFileGame = importFileGame;
        this.gameRepository = new GameRepositoryInMemory();
        this.importCSVGame = new ImportCSVGameStub(this.gameRepository);

        this.gameService = new CreateGameImpl(this.gameRepository);
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

        return ResponseEntity.ok(this.gameService.createGame(game));
    }

    @GetMapping("")
    public List<Game> allGame() {
        return null;
    }

    @GetMapping("/test")
    public String test() throws IOException, FileException, FileDataException {
        Resource resource = new ClassPathResource("static/test.csv");
        this.importFileGame.importFileGame(resource.getInputStream());

        return "Hello World";
    }
}
