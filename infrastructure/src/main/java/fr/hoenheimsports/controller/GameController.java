package fr.hoenheimsports.controller;

import fr.hoenheimsports.gamedomain.api.DisplayGame;
import fr.hoenheimsports.gamedomain.api.ImportFileGame;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.service.GameServiceApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {


    private final GameServiceApplication gameService;

    public GameController(GameServiceApplication gameService) {
        this.gameService = gameService;
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
        return this.gameService.displayGame();
    }

    @GetMapping("/import")
    public ResponseEntity<List<Game>> importFile(@RequestParam("csv") MultipartFile csvFile)  {
        try {
            return ResponseEntity.ok(this.gameService.importFile(csvFile));
        } catch (FileException e) {
            return ResponseEntity.badRequest().build();
        } catch (FileDataException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
