package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.game.view.GenderDTO;
import fr.hoenheimsports.dto.game.view.TeamDTO;
import fr.hoenheimsports.service.TeamServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamServiceApplication teamServiceApplication;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    public TeamController(TeamServiceApplication teamServiceApplication) {
        this.teamServiceApplication = teamServiceApplication;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> displayTeam(@PathVariable String id) {
        System.out.println("team" + id + " demandé");
        return ResponseEntity.ok(this.teamServiceApplication.displayTeam(id));
    }


    @GetMapping("")
    public ResponseEntity<List<TeamDTO>> displayTeams(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String category
            ) throws MissingServletRequestParameterException {
        System.out.println("Teams demandé");
        List<TeamDTO> teams;
        if((gender != null) ^ (category != null)) {
            throw new MissingServletRequestParameterException("gender",GenderDTO.class.getTypeName());
        }
        if(gender == null) {
            teams = this.teamServiceApplication.displayTeams();
        } else {
            teams = this.teamServiceApplication.displayTeams(category,gender);
        }
        return ResponseEntity.ok(teams);
    }
}
