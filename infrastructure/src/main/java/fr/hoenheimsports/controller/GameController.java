package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.service.GameServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<List<GameDTO>> displayGames() {
        return ResponseEntity.ok(this.gameService.displayGames());
    }

    @GetMapping("/{code}")
    public ResponseEntity<GameDTO> displayGames(@PathVariable String code) {
        return ResponseEntity.ok(this.gameService.displayGame(code));
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
