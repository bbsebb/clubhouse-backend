package fr.hoenheimsports.controller;

import fr.hoenheimsports.gamedomain.CreateGameImpl;
import fr.hoenheimsports.gamedomain.ImportFileGameImpl;
import fr.hoenheimsports.gamedomain.api.ImportFileGame;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import fr.hoenheimsports.service.CSVToGames;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final CreateGame gameService;
    private final FileToGames csvToGame;
    private final GameRepository gameRepository;
    private final FileToGames fileToGames;
    private final ImportFileGame importFileGame;

    public GameController(FileToGames fileToGames) {
        this.fileToGames = fileToGames;
        this.gameRepository = new GameRepositoryInMemory();
        this.csvToGame = new CSVToGames();

        this.gameService = new CreateGameImpl(this.gameRepository);
        this.importFileGame = new ImportFileGameImpl(fileToGames,this.gameRepository);
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
                .withDate(LocalDate.now())
                .build();

        return ResponseEntity.ok(this.gameService.createGame(game));
    }

    @GetMapping("")
    public List<Game> allGame() {
        return null;
    }

    @GetMapping("/test")
    public List<Game> test() throws IOException, FileException, FileDataException {
        Resource resource = new ClassPathResource("static/test2.csv");
        return this.importFileGame.importFileGame(resource.getInputStream());
    }
}
