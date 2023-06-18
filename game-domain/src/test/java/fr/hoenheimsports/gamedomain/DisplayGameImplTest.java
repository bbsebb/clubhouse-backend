package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.DisplayGame;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayGameImplTest {
    @Test
    public void testFindAllGame() {
        GameRepositoryInMemory gameRepository = new GameRepositoryInMemory();

        Game game1 = GameBuilder.builder()
                .withCode("test1")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build();
        Game game2 = GameBuilder.builder()
                .withCode("test2")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build();
        gameRepository.save(game1);
        gameRepository.save(game2);

        DisplayGame displayGame = new DisplayGameImpl(gameRepository);

        List<Game> allGames = displayGame.findAllGame();

        assertNotNull(allGames);
        assertEquals(2,allGames.size());
        assertTrue(allGames.contains(game1));
        assertTrue(allGames.contains(game2));
    }
}