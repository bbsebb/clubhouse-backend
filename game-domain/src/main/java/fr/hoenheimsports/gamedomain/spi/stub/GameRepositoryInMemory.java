package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRepositoryInMemory implements GameRepository {
    private Map<String,Game> games = new HashMap<>();

    @Override
    public Game save(Game game) {
        this.games.put(game.getCode(),game);
        return game;
    }

    @Override
    public Game findById(String gameCode) {
        return this.games.get(gameCode);
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(this.games.values());
    }

    @Override
    public void update(Game game) {
        this.games.put(game.getCode(),game);
    }

    @Override
    public void delete(String gameCode) {
        this.games.remove(gameCode);
    }
}
