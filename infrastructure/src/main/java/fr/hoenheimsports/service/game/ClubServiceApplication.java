package fr.hoenheimsports.service.game;

import fr.hoenheimsports.dto.game.view.ClubDTO;
import fr.hoenheimsports.gamedomain.api.ClubDisplay;
import fr.hoenheimsports.service.game.mapper.ClubMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClubServiceApplication {

    private final ClubDisplay clubDisplay;
    private final ClubMapper clubMapper;

    public ClubServiceApplication(ClubDisplay clubDisplay, ClubMapper clubMapper) {
        this.clubDisplay = clubDisplay;
        this.clubMapper = clubMapper;
    }

    public List<ClubDTO> displayClubs() {
        return this.clubDisplay.findAll().stream().map(this.clubMapper::clubToClubDTO).toList();
    }

    public ClubDTO displayClub(String code) {
        return this.clubDisplay.findByCode(code).map(this.clubMapper::clubToClubDTO).orElse(null);
    }
}
