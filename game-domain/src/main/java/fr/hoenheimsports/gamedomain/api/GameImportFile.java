package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;

import java.io.InputStream;
import java.util.List;

public interface GameImportFile {
    List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException;
}
