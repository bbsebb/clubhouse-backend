package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.DisplayGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.util.List;
@DomainService
public class DisplayGameImpl implements DisplayGame {

    private GameRepository gameRepository;

    public DisplayGameImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAllGame() {
        return this.gameRepository.findAll();
    }
}
