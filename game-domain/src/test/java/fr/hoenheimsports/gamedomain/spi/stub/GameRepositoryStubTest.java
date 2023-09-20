package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryStubTest {

    private GameRepositoryStub gameRepositoryStub;

    @BeforeEach
    void setUp() {
        gameRepositoryStub = new GameRepositoryStub();
    }

    @Test
    void testSave() {
        Game game = createDummyGame();
        Game savedGame = gameRepositoryStub.save(game);
        assertEquals(game, savedGame, "The saved game should match the original game");
    }

    @Test
    void testFindById() {
        Game game = createDummyGame();
        gameRepositoryStub.save(game);
        Optional<Game> foundGame = gameRepositoryStub.findById(game.getCode());

        assertTrue(foundGame.isPresent(), "Game should be found by ID");
        assertEquals(game, foundGame.get(), "Found game should match the saved game");
    }

    @Test
    void testFindAll() {
        Game game1 = createDummyGame();
        Game game2 = createDummyGame();

        gameRepositoryStub.save(game1);
        gameRepositoryStub.save(game2);

        List<Game> games = gameRepositoryStub.findAll();
        assertTrue(games.contains(game1), "Games list should contain the first saved game");
        assertTrue(games.contains(game2), "Games list should contain the second saved game");
    }

    @Test
    void testUpdate() {
        Game game = createDummyGame();
        gameRepositoryStub.save(game);

        game.setDate(LocalDate.now().plusDays(5)); // Modifying the date for update
        gameRepositoryStub.update(game);

        Optional<Game> updatedGame = gameRepositoryStub.findById(game.getCode());
        assertEquals(LocalDate.now().plusDays(5), updatedGame.get().getDate(), "The date should be updated in the repository");
    }

    @Test
    void testDelete() {
        Game game = createDummyGame();
        gameRepositoryStub.save(game);
        gameRepositoryStub.delete(game.getCode());

        Optional<Game> foundGame = gameRepositoryStub.findById(game.getCode());
        assertFalse(foundGame.isPresent(), "Game should be deleted from the repository");
    }

    private Game createDummyGame() {
        return new Game(
                "code1",
                Pool.UNKNOWN,
                Season.SEASON_2022_2023,
                Day.SINGLE_DAY_GAME,
                Week.NOW,
                Hall.UNKNOWN,
                Referees.UNKNOWN,
                Team.UNKNOWN,
                Team.UNKNOWN,
                new Score(0, 0),
                FDME.UNKNOWN,
                LocalDate.now(),
                LocalTime.now()
        );
    }
}
