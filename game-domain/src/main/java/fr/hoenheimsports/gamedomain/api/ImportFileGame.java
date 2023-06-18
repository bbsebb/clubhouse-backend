package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;

import java.io.InputStream;
import java.util.List;

public interface ImportFileGame {
    public List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException;
}
