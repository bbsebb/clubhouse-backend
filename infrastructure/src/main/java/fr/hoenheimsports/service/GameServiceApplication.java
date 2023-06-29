package fr.hoenheimsports.service;

import fr.hoenheimsports.dto.game.GameDTO;
import fr.hoenheimsports.gamedomain.api.GameDisplay;
import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.service.mapper.GameMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class GameServiceApplication {
    private final GameImportFile gameImportFile;
    private final GameDisplay gameDisplay;
    private final GameMapper gameMapper;



    public GameServiceApplication(GameImportFile gameImportFile, GameDisplay gameDisplay, GameMapper gameMapper) {
        this.gameImportFile = gameImportFile;
        this.gameDisplay = gameDisplay;
        this.gameMapper = gameMapper;
    }

    public List<GameDTO> importFile(MultipartFile csvFile) throws FileException, FileDataException {
        InputStream inputStream = null;
        try {
            inputStream = csvFile.getInputStream();
        } catch (IOException ioe) {
            throw new FileException();
        }
        return this.gameImportFile.importFileGame(inputStream).stream().map(this.gameMapper::gameToGameDTO).toList();
    }

    public GameDTO createGame(GameDTO gameDTO) {
        return null;
    }

    public List<GameDTO> displayGame() {
        return this.gameDisplay.findAllGame().stream().map(this.gameMapper::gameToGameDTO).toList();
    }
}
