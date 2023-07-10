package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.repository.entity.game.CategoryEntity;
import fr.hoenheimsports.repository.entity.game.SeasonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {
    public Season seasonEntityToSeason(SeasonEntity seasonEntity);
    public SeasonEntity seasonToSeasonEntity(Season season);
}
