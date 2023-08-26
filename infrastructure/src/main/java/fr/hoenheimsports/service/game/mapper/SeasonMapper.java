package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.repository.game.entity.game.SeasonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {
    public Season seasonEntityToSeason(SeasonEntity seasonEntity);
    public SeasonEntity seasonToSeasonEntity(Season season);
}
