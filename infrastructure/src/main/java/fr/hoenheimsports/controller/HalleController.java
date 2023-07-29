package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.game.view.HalleDTO;
import fr.hoenheimsports.service.HalleServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/halles")
public class HalleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    private final HalleServiceApplication halleServiceApplication;

    public HalleController(HalleServiceApplication halleServiceApplication) {
        this.halleServiceApplication = halleServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<HalleDTO>> displayHalles(@RequestParam(value = "club_code",required = false)String clubCode) {
        List<HalleDTO> halles;
        if(clubCode != null) {
            halles = this.halleServiceApplication.displayHalles(clubCode);
        } else {
            halles = this.halleServiceApplication.displayHalles();
        }
        return ResponseEntity.ok(halles);
    }
}
