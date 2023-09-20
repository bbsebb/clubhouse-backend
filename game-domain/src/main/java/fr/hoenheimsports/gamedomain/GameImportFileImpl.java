package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.io.InputStream;
import java.util.List;

@DomainService
public class GameImportFileImpl implements GameImportFile {

    final private  FileToGames fileToGames;

    public GameImportFileImpl(FileToGames fileToGames) {
        this.fileToGames = fileToGames;
    }

    @Override
    public List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException, CoachNotFoundException, ClubNotFoundException, HallNotFoundException, TeamNotFoundException, GameNotFoundException {

        return fileToGames.importFile(fileInputStream);
    }


}
