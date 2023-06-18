package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;
import fr.hoenheimsports.gamedomain.spi.exception.FileException;

import java.io.InputStream;
import java.util.List;

public interface FileToGames {

    public List<Game> fileToGames(InputStream fileStream) throws  FileDataException, FileException;

}
