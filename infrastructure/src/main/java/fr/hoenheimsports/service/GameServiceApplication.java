package fr.hoenheimsports.service;

import fr.hoenheimsports.gamedomain.api.GameDisplay;
import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class GameServiceApplication {
    private final GameImportFile gameImportFile;
    private final GameDisplay gameDisplay;

    public GameServiceApplication(GameImportFile gameImportFile, GameDisplay gameDisplay) {
        this.gameImportFile = gameImportFile;
        this.gameDisplay = gameDisplay;
    }

    public List<Game> importFile(MultipartFile csvFile) throws FileException, FileDataException {
        InputStream inputStream = null;
        try {
            inputStream = csvFile.getInputStream();
        } catch (IOException ioe) {
            throw new FileException();
        }
        return this.gameImportFile.importFileGame(inputStream);
    }

    public Game createGame(Game game) {
        return null;
    }

    public List<Game> displayGame() {
        return this.gameDisplay.findAllGame();
    }
}
