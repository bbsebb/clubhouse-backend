package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;

import java.io.InputStream;
import java.util.List;

public interface FileToGames {

    List<Game> fileToGames(InputStream fileStream) throws  FileDataException, FileException;

}
