package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.builder.AddressBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;

import java.util.*;
import java.util.stream.Stream;
@Stub
public class GameRepositoryInMemory implements GameRepository {
    private final Map<String,Game> games = new HashMap<>();

    public GameRepositoryInMemory() {
    }

    @Override
    public Game save(Game game) {
        this.games.put(game.getCode(),game);
        return game;
    }

    @Override
    public Optional<Game> findById(String gameCode) {
        return Optional.ofNullable(this.games.get(gameCode));
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

    public void populate(List<Game> games) {
        games.forEach(game -> this.games.put(game.getCode(),game));
    }


}
