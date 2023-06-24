package fr.hoenheimsports.service;

import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceApplicationTest {

    @Mock
    private GameImportFile gameImportFile;

    @InjectMocks
    private GameServiceApplication gameServiceApplication;

    @Test
    void importFile() throws IOException, FileException, FileDataException {

        String csvData = "code,competition\nSAEHOBZ,coupe de france\nSAEEPFO,67-01 1ere division\n";
        byte[] csvBytes = csvData.getBytes(StandardCharsets.UTF_8);

        MockMultipartFile multipartFile = new MockMultipartFile("csvFile", "test.csv", "text/csv", csvBytes);

        List<Game> expectedGames = createExpectedGames();

        when(gameImportFile.importFileGame(any(InputStream.class))).thenReturn(expectedGames);
        List<Game> actualGames = gameServiceApplication.importFile(multipartFile);

        assertEquals(expectedGames.size(), actualGames.size());

    }

    private List<Game> createExpectedGames() {
        List<Game> games = new ArrayList<>();
        games.add( GameBuilder.builder()
                .withCode("test1")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build());
        games.add( GameBuilder.builder()
                .withCode("test3")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build());
        games.add( GameBuilder.builder()
                .withCode("test2")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build());

        return games;
    }
}