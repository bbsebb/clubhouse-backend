package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.CreateGame;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateGameImplTest {
    @Test
    public void testCreateGame() {
        // Créez une instance de GameRepositoryInMemory ou utilisez une autre implémentation du GameRepository
        GameRepositoryInMemory gameRepository = new GameRepositoryInMemory();
        // Créez une instance de CreateGameImpl en utilisant le GameRepository créé
        CreateGame createGame = new CreateGameImpl(gameRepository);


        // Créez un jeu pour le test
        Game game = GameBuilder.builder()
                .withCode("test1")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build();;

        // Appelez la méthode createGame() pour créer le jeu
        Game createdGame =createGame.createGame(game);

        // Vérifiez que le jeu a été créé avec succès
        assertNotNull(createdGame);
        assertEquals(game.getCode(), createdGame.getCode());

        // Vérifiez que le jeu a été enregistré dans le GameRepository
        Game retrievedGame = gameRepository.findById(game.getCode());
        assertNotNull(retrievedGame);
        assertEquals(game.getCode(), retrievedGame.getCode());
    }

}