package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.GameDTO;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.repository.entity.game.GameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    public Game gameEntityToGame(GameEntity gameEntity);
    public GameEntity gameToGameEntity(Game game);
    public GameDTO gameToGameDTO(Game game);
    public Game gameDTOToGame(GameDTO gameDTO);
}
