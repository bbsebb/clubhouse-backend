package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameDisplay {
    List<Game> findAllGame();

    Optional<Game> findByCode(String code);
}
