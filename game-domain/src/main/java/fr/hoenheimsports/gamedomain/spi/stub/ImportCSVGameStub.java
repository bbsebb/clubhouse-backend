package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.spi.ImportFileGame;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.GameRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportCSVGameStub implements ImportFileGame {

    private GameRepository gameRepository;

    public ImportCSVGameStub(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> importFileGame(InputStream fileStream) throws IOException {
        String separator = ";";

        List<List<String>> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

        String line;
        while ((line = reader.readLine()) != null) {
            Arrays.stream(line.split(separator)).forEach(System.out::println);
        }

        return null;
    }


}
