package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.api.ImportFileGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;

import java.io.InputStream;
import java.util.List;

public class ImportFileGameImpl implements ImportFileGame {

    final private  FileToGames fileToGames;
    final private GameRepository gameRepository;

    public ImportFileGameImpl(FileToGames fileToGames, GameRepository gameRepository) {
        this.fileToGames = fileToGames;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException {

        return fileToGames.fileToGames(fileInputStream).stream().map(gameRepository::save).toList();
    }


}
