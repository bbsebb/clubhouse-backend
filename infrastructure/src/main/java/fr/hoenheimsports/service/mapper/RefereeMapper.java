package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.repository.entity.game.RefereeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RefereeMapper {
    @Mapping(source = "id", target = "id")
    public Referee refereeEntityToReferee(RefereeEntity refereeEntity);
    @Mapping(source = "id", target = "id")
    public RefereeEntity refereeToRefereeEntity(Referee referee);
}
