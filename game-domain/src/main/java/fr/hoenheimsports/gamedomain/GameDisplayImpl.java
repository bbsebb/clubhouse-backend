package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameDisplay;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.util.List;
@DomainService
public class GameDisplayImpl implements GameDisplay {

    private final GameRepository gameRepository;

    public GameDisplayImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAllGame() {
        return this.gameRepository.findAll();
    }
}
