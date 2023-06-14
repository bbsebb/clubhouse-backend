package fr.hoenheimsports.model;

import fr.hoenheimsports.model.api.GameService;
import fr.hoenheimsports.model.spi.GameRepository;

import java.util.List;

public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game gameCreate(Game game) {
        return this.gameRepository.save(game);
    }

    @Override
    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }
}
