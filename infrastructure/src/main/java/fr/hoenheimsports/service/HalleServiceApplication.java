package fr.hoenheimsports.service;

import fr.hoenheimsports.dto.game.view.HalleDTO;
import fr.hoenheimsports.gamedomain.api.HalleDisplay;
import fr.hoenheimsports.service.mapper.HalleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HalleServiceApplication {
    private final HalleDisplay halleDisplay;
    private final HalleMapper halleMapper;

    public HalleServiceApplication(HalleDisplay halleDisplay,
                                   HalleMapper halleMapper) {
        this.halleDisplay = halleDisplay;
        this.halleMapper = halleMapper;
    }

    public List<HalleDTO> displayHalles(String clubCode) {
        return this.halleDisplay.findByClubCode(clubCode).stream().map(halleMapper::halleToHalleDTO).collect(Collectors.toList());
    }

    public List<HalleDTO> displayHalles() {
        return this.halleDisplay.findAll().stream().map(halleMapper::halleToHalleDTO).collect(Collectors.toList());
    }
}
