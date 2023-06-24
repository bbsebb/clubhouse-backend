package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Game;

import java.util.List;

public interface GameDisplay {
    List<Game> findAllGame();
}
