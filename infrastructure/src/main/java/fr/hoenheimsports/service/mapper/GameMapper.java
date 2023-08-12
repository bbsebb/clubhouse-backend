package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.repository.game.entity.game.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    public Game gameEntityToGame(GameEntity gameEntity);
    public GameEntity gameToGameEntity(Game game);
    @Mapping(target = "isPlayed", expression = "java(game.isPlayed())")
    @Mapping(target = "dateTime", expression = "java(game.getDate() != null && game.getTime() != null ? LocalDateTime.of(game.getDate(), game.getTime()) : null)")
    public GameDTO gameToGameDTO(Game game);


    default Game gameDTOToGame(GameDTO gameDTO) {
        throw new UnsupportedOperationException();
    }
    default Game importCsvGameDTOToGame(ImportCsvGameDTO importCsvGameDTO) throws FileDataException {
        throw new UnsupportedOperationException();
    }


}
