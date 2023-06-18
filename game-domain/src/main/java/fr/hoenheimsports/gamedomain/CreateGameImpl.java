package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

public class CreateGameImpl implements CreateGame {


    private GameRepository gameRepository;

    public CreateGameImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game game) {
        return this.gameRepository.save(game);
    }
}
