package fr.hoenheimsports.service;

import fr.hoenheimsports.gamedomain.api.DisplayGame;
import fr.hoenheimsports.gamedomain.api.ImportFileGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class GameServiceApplication {
    private final ImportFileGame importFileGame;
    private final DisplayGame displayGame;

    public GameServiceApplication(ImportFileGame importFileGame, DisplayGame displayGame) {
        this.importFileGame = importFileGame;
        this.displayGame = displayGame;
    }

    public List<Game> importFile(MultipartFile csvFile) throws FileException, FileDataException {
        InputStream inputStream = null;
        try {
            inputStream = csvFile.getInputStream();
        } catch (IOException ioe) {
            throw new FileException();
        }
        return this.importFileGame.importFileGame(inputStream);
    }

    public Game createGame(Game game) {
        return null;
    }

    public List<Game> displayGame() {
        return this.displayGame.findAllGame();
    }
}
