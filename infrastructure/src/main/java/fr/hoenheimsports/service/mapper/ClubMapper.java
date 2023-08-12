package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.view.ClubDTO;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.repository.game.entity.game.ClubEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClubMapper {

    public Club clubEntityToClub(ClubEntity clubEntity);
    public ClubEntity clubToClubEntity(Club club);

    public ClubDTO clubToClubDTO(Club club);






}
