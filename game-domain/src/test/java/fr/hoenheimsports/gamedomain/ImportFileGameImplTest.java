package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.ImportFileGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import fr.hoenheimsports.gamedomain.spi.stub.CSVToGamesStub;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import fr.hoenheimsports.gamedomain.spi.stub.KitOfGames;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportFileGameImplTest {

    @Test
    public void testImportFileGame() throws FileDataException, FileException {
        CSVToGamesStub csvToGamesStub = new CSVToGamesStub();

        List<Game> games = KitOfGames.getKitOfGames();
        csvToGamesStub.populate(games);

        GameRepository gameRepository = new GameRepositoryInMemory();

        ImportFileGame importFileGame = new ImportFileGameImpl(csvToGamesStub, gameRepository);

        String fileData = "game1,game2,game3";
        InputStream inputStream = new ByteArrayInputStream(fileData.getBytes());

        List<Game> importedGames = importFileGame.importFileGame(inputStream);

        assertEquals(games.size(), importedGames.size());

        for (Game game : importedGames) {
            Game retrievedGame = gameRepository.findById(game.getCode());
            assertNotNull(retrievedGame);
            assertEquals(game.getCode(), retrievedGame.getCode());
        }
    }

}