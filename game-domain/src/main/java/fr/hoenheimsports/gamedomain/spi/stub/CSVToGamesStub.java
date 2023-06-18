package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.gamedomain.spi.exception.FileDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CSVToGamesStub implements FileToGames {

    private GameRepository gameRepository;

    public CSVToGamesStub(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> fileToGames(InputStream fileStream) throws FileDataException {
        String separator = ";";

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

        String line;

        try {
            while ((line = reader.readLine()) != null) {
                Arrays.stream(line.split(separator)).forEach(System.out::println);
            }
        } catch (IOException ioe) {
            throw new FileDataException();
        }

        return null;
    }


}
