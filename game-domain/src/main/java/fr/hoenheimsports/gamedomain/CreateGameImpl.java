package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

public class CreateGameImpl implements CreateGame {

    public final static GameBuilder gameBuilder = new GameBuilder();
    private GameRepository gameRepository;

    public CreateGameImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    @Override
    public Game createGame(Game game) {
        return this.gameRepository.save(game);
    }
}
