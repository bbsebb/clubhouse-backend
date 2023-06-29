package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.game.GameDTO;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.service.GameServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameServiceApplication gameService;

    public GameController(GameServiceApplication gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/create")
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {


        return ResponseEntity.ok(this.gameService.createGame(gameDTO));
    }

    @GetMapping("")
    public List<GameDTO> displayGames() {
        return this.gameService.displayGame();
    }

    @PostMapping("/import")
    public ResponseEntity<List<GameDTO>> importFile(@RequestParam("csv") MultipartFile csvFile)  {
        try {
            return ResponseEntity.ok(this.gameService.importFile(csvFile));
        } catch (FileException e) {
            LOGGER.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (FileDataException e) {
            LOGGER.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
