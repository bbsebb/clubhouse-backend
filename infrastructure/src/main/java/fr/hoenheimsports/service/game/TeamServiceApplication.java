package fr.hoenheimsports.service.game;

import fr.hoenheimsports.dto.game.view.TeamDTO;
import fr.hoenheimsports.gamedomain.api.TeamDisplay;
import fr.hoenheimsports.service.game.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceApplication {
    private final TeamDisplay teamDisplay;
    private final TeamMapper teamMapper;


    public TeamServiceApplication(TeamDisplay teamDisplay, TeamMapper teamMapper) {
        this.teamDisplay = teamDisplay;
        this.teamMapper = teamMapper;
    }

    public List<TeamDTO> displayTeams() {
        return this.teamDisplay.findAll().stream().map(this.teamMapper::teamDTOToTeam).collect(Collectors.toList());
    }

    public List<TeamDTO> displayTeams(String categoryName, String genderName) {

        return this.teamDisplay.findByCategoryAndGender(categoryName,genderName).stream().map(this.teamMapper::teamDTOToTeam).collect(Collectors.toList());
    }

    public TeamDTO displayTeam(String id) {
        return this.teamDisplay.findById(id).map(teamMapper::teamDTOToTeam).orElse(null);
    }
}
