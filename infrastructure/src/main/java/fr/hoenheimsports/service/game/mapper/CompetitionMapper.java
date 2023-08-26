package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.repository.game.entity.game.CompetitionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    public Competition competitionEntityToCompetition(CompetitionEntity competitionEntity);
    public CompetitionEntity competitionToCompetitionEntity(Competition competition);
}
