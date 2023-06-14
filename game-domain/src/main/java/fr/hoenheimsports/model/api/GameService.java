package fr.hoenheimsports.model.api;

import fr.hoenheimsports.model.Game;

import java.util.List;

public interface GameService {
    public Game gameCreate(Game game);

    public List<Game> findAll();
}
