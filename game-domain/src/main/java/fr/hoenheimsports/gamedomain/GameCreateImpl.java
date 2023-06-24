package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameCreate;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
@DomainService
public class GameCreateImpl implements GameCreate {


    private final GameRepository gameRepository;

    public GameCreateImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game game) {
        return this.gameRepository.save(game);
    }
}
