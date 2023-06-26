package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.repository.entity.game.ClubEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    public Club clubEntityToClub(ClubEntity clubEntity);
    public ClubEntity clubToClubEntity(Club club);
}
