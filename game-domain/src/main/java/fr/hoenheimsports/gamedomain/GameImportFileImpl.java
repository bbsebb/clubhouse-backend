package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;

import java.io.InputStream;
import java.util.List;

@DomainService
public class GameImportFileImpl implements GameImportFile {

    final private  FileToGames fileToGames;
    final private GameRepository gameRepository;

    public GameImportFileImpl(FileToGames fileToGames, GameRepository gameRepository) {
        this.fileToGames = fileToGames;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException {

        return fileToGames.fileToGames(fileInputStream);
    }


}
