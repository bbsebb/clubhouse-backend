package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findById(String gameCode);

    List<Game> findAll();

    void update(Game game);

    void delete(String gameCode);
}
