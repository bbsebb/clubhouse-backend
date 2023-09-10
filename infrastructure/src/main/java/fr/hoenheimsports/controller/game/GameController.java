package fr.hoenheimsports.controller.game;

import fr.hoenheimsports.dto.game.create.GameCreateDTO;
import fr.hoenheimsports.dto.game.filter.TimeslotFilterDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.service.game.GameServiceApplication;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    public ResponseEntity<Map<String,String>> createGame(@RequestBody GameCreateDTO gameCreateDTO) {
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
    public ResponseEntity<Void> importFile(@RequestParam("files") MultipartFile[] csvFiles)  {
        try {
            for(MultipartFile csvFile : csvFiles) {
                this.gameService.importFile(csvFile);
            }
            return ResponseEntity.ok().build();
        } catch (FileException e) {
            LOGGER.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (FileDataException e) {
            LOGGER.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
