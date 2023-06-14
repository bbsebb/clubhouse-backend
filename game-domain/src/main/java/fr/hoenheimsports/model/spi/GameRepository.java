package fr.hoenheimsports.model.spi;

import fr.hoenheimsports.model.Game;

import java.util.List;

public interface GameRepository {
    Game save(Game game);

    Game findById(String gameCode);

    List<Game> findAll();

    void update(Game game);

    void delete(String gameCode);
}
