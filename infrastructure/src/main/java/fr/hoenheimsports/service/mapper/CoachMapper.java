package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.repository.game.entity.game.CoachEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoachMapper {
    @Mapping(source = "id", target = "id")
    public Coach coachEntityToCoach(CoachEntity coachEntity);
    @Mapping(source = "id", target = "id")
    public CoachEntity coachToCoachEntity(Coach coach);
}
