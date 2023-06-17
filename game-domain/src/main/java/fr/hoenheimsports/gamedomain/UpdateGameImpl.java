package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.UpdateGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

public class UpdateGameImpl implements UpdateGame {

    private GameRepository gameRepository;

    public UpdateGameImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game updateGame(Game game) {
        return this.gameRepository.save(game);
    }
}
