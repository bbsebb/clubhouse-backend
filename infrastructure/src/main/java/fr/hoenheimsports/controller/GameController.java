package fr.hoenheimsports.controller;

import fr.hoenheimsports.Test;
import fr.hoenheimsports.model.Game;
import fr.hoenheimsports.model.GameServiceImpl;
import fr.hoenheimsports.model.api.GameService;
import fr.hoenheimsports.model.spi.stub.GameRepositoryInMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private GameService gameService;

    public GameController() {
        this.gameService = new GameServiceImpl(new GameRepositoryInMemory());
    }

    @GetMapping("/create")
    public List<Game> createGame() {
        return this.gameService.findAll();
    }

    @GetMapping("")
    public String test() {
        return Test.TEST;
    }
}
