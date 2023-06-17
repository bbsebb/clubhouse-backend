package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;

import java.util.List;

public interface GameRepository {
    Game save(Game game);

    Game findById(String gameCode);

    List<Game> findAll();

    void update(Game game);

    void delete(String gameCode);
}
