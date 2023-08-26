package fr.hoenheimsports.controller.game;

import fr.hoenheimsports.dto.game.view.HallDTO;
import fr.hoenheimsports.service.game.HallServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("gameHallController")
@RequestMapping("/api/game-halls")
public class HallController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    private final HallServiceApplication hallServiceApplication;

    public HallController(HallServiceApplication halleServiceApplication) {
        this.hallServiceApplication = halleServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<HallDTO>> displayHalls(@RequestParam(value = "club_code",required = false)String clubCode) {
        List<HallDTO> hallDTOS;
        if(clubCode != null) {
            hallDTOS = this.hallServiceApplication.displayHalls(clubCode);
        } else {
            hallDTOS = this.hallServiceApplication.displayHalls();
        }
        return ResponseEntity.ok(hallDTOS);
    }
}
