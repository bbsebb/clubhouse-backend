package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.FileToGames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CSVToGamesStub implements FileToGames {
    List<Game> games = new ArrayList<>();
    @Override
    public List<Game> fileToGames(InputStream fileStream) {
        return this.games;
    }

    public void populate(List<Game> games) {
        this.games = games;
    }


}
