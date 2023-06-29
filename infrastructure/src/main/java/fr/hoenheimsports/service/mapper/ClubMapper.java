package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.repository.entity.game.ClubEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClubMapper {

    public Club clubEntityToClub(ClubEntity clubEntity);
    public ClubEntity clubToClubEntity(Club club);






}
