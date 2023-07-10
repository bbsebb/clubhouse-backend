package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.gamedomain.spi.stub.CSVToGamesStub;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import fr.hoenheimsports.gamedomain.spi.stub.KitOfGames;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameImportFileImplTest {

    @Test
    public void testImportFileGame() throws FileDataException, FileException {
        CSVToGamesStub csvToGamesStub = new CSVToGamesStub();

        List<Game> games = KitOfGames.getKitOfGames();
        csvToGamesStub.populate(games);

        GameRepository gameRepository = new GameRepositoryInMemory();

        GameImportFile gameImportFile = new GameImportFileImpl(csvToGamesStub, gameRepository);

        String fileData = "game1,game2,game3";
        InputStream inputStream = new ByteArrayInputStream(fileData.getBytes());

        List<Game> importedGames = gameImportFile.importFileGame(inputStream);

        assertEquals(games.size(), importedGames.size());

    }

}