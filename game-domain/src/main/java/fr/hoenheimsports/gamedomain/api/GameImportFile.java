package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.gamedomain.model.Game;

import java.io.InputStream;
import java.util.List;

public interface GameImportFile {
    List<Game> importFileGame(InputStream fileInputStream) throws FileDataException, FileException, CoachNotFoundException, ClubNotFoundException, HallNotFoundException, TeamNotFoundException, GameNotFoundException;
}
