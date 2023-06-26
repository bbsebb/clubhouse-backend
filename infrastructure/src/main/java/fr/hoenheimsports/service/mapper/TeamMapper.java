package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Team;
import fr.hoenheimsports.repository.entity.game.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(source = "id", target = "id")
    public Team teamEntityToTeam(TeamEntity teamEntity);

    public TeamEntity teamToTeamEntity(Team team);
}
