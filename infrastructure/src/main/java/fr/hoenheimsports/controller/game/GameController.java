package fr.hoenheimsports.controller.game;

import fr.hoenheimsports.dto.game.create.GameCreateDTO;
import fr.hoenheimsports.dto.booking.filter.TimeslotFilterDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.service.game.GameServiceApplication;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameServiceApplication gameService;

    public GameController(GameServiceApplication gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createGame(@RequestBody GameCreateDTO gameCreateDTO) throws GameAlreadyExistsException, TeamNotFoundException, ClubNotFoundException, CategoryNotFoundException {
        GameDTO game = this.gameService.createGame(gameCreateDTO);
        return ResponseEntity.ok(Map.of("code",game.code()));
    }

    @GetMapping("")
    public ResponseEntity<List<GameDTO>> displayGames(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end) {

        if(start != null && end != null) {
            return ResponseEntity.ok(this.gameService.displayGames(new TimeslotFilterDTO(start,end)));
        }
        return ResponseEntity.ok(this.gameService.displayGames());
    }



    @GetMapping("/{code}")
    public ResponseEntity<GameDTO> displayGames(@PathVariable String code) {
        return ResponseEntity.ok(this.gameService.displayGame(code));
    }

    @PostMapping("/import")
    @Transactional
    public ResponseEntity<List<GameDTO>> importFile(@RequestParam("files") MultipartFile[] csvFiles)    throws Exception {
        try {
            List<GameDTO> games = new ArrayList<>();
            for(MultipartFile csvFile : csvFiles) {
                games.addAll(this.gameService.importFile(csvFile));
            }
            return ResponseEntity.ok(games);
        } catch (FileException | FileDataException e) {
            LOGGER.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
