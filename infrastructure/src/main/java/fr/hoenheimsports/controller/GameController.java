package fr.hoenheimsports.controller;

import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.service.GameServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<Game> displayGames() {
        return this.gameService.displayGame();
    }

    @PostMapping("/import")
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
