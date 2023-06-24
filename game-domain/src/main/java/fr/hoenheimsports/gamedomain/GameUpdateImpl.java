package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameUpdate;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
@DomainService
public class GameUpdateImpl implements GameUpdate {

    private final GameRepository gameRepository;

    public GameUpdateImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game updateGame(Game game) {
        return this.gameRepository.save(game);
    }
}
