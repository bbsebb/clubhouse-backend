package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.gamedomain.model.Game;

import java.io.InputStream;
import java.util.List;

public interface FileToGames {

    List<Game> importFile(InputStream fileStream) throws FileDataException, FileException, CoachNotFoundException, ClubNotFoundException, HallNotFoundException, TeamNotFoundException, GameNotFoundException;

}
