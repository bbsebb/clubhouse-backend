package fr.hoenheimsports.service.game;

import fr.hoenheimsports.dto.game.view.HallDTO;
import fr.hoenheimsports.gamedomain.api.HallDisplay;
import fr.hoenheimsports.service.game.mapper.HallMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("gameHallServiceApplication")
public class HallServiceApplication {
    private final HallDisplay hallDisplay;
    private final HallMapper hallMapper;

    public HallServiceApplication(HallDisplay hallDisplay,
                                  HallMapper hallMapper) {
        this.hallDisplay = hallDisplay;
        this.hallMapper = hallMapper;
    }

    public List<HallDTO> displayHalls(String clubCode) {
        return this.hallDisplay.findByClubCode(clubCode).stream().map(hallMapper::hallToHalleDTO).collect(Collectors.toList());
    }

    public List<HallDTO> displayHalls() {
        return this.hallDisplay.findAll().stream().map(hallMapper::hallToHalleDTO).collect(Collectors.toList());
    }
}
