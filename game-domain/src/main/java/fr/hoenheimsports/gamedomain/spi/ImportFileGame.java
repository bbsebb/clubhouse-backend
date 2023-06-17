package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ImportFileGame {

    public List<Game> importFileGame(InputStream fileStream) throws IOException, FileDataException, FileException;

}
