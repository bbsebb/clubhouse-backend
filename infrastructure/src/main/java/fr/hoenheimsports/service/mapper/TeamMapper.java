package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.view.TeamDTO;
import fr.hoenheimsports.gamedomain.model.Team;
import fr.hoenheimsports.repository.game.entity.game.TeamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    public Team teamEntityToTeam(TeamEntity teamEntity);

    public TeamEntity teamToTeamEntity(Team team);

    public Team teamToTeamDTO(TeamDTO teamDTO);

    public TeamDTO teamDTOToTeam(Team team);
}
