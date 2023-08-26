package fr.hoenheimsports.controller.game;

import fr.hoenheimsports.dto.game.view.ClubDTO;
import fr.hoenheimsports.service.game.ClubServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubServiceApplication clubServiceApplication;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    public ClubController(ClubServiceApplication clubServiceApplication) {
        this.clubServiceApplication = clubServiceApplication;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ClubDTO> displayTeam(@PathVariable String code) {
        return ResponseEntity.ok(this.clubServiceApplication.displayClub(code));
    }


    @GetMapping("")
    public ResponseEntity<List<ClubDTO>> displayTeams()  {
        return ResponseEntity.ok(this.clubServiceApplication.displayClubs());
    }
}
