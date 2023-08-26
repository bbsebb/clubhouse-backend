package fr.hoenheimsports.service.game;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.builder.RefereesBuilder;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.spi.GameRepository;
import fr.hoenheimsports.service.game.mapper.GameMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CSVFileToGames implements FileToGames {
    private static final List<String> headerNoPlayed = Arrays.asList(
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "club hote",
            "arb1 designe",
            "arb2 designe",
            "observateur",
            "delegue",
            "code renc",
            "nom salle",
            "adresse salle",
            "CP",
            "Ville",
            "colle",
            "Coul. Rec",
            "Coul. Gard. Rec",
            "Coul. Vis",
            "Coul. Gard. Vis",
            "Ent. Rec",
            "Tel Ent. Rec",
            "Corresp. Rec",
            "Tel Corresp. Rec",
            "Ent. Vis",
            "Tel Ent. Vis",
            "Corresp. Vis",
            "Tel Corresp. Vis",
            "Num rec",
            "Num vis"
    );

    private static final List<String> headerPlayed = Arrays.asList(
            "semaine",
            "num poule",
            "competition",
            "poule",
            "J",
            "le",
            "horaire",
            "club rec",
            "club vis",
            "sc rec",
            "sc vis",
            "fdme rec",
            "fdme vis",
            "pen. rec",
            "pen. vis",
            "forf. rec",
            "forf. vis",
            "arb1 designe",
            "arb2 designe",
            "arb1 sifle",
            "arb2 sifle",
            "secretaire",
            "chronometreur",
            "observateur",
            "delegue",
            "resp salle",
            "tuteur table",
            "code renc",
            "Num rec",
            "Num vis",
            "Etat",
            "Forfait",
            "Penalite",
            "FDME",
            "Date Arrivee"
    );

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;


    public CSVFileToGames(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }
    @Transactional
    @Override
    public List<Game> fileToGames(InputStream fileStream) throws FileDataException, FileException {
        List<ImportCsvGameDTO> importCSVGameDTOS = this.fileToImportCSVGameDTO(fileStream);
        List<Game> games = new ArrayList<>();
        for (ImportCsvGameDTO csvGameDTO: importCSVGameDTOS) {
            Game game =  this.gameMapper.importCsvGameDTOToGame(csvGameDTO);

            Optional<Game> previousGame = this.gameRepository.findById(game.getCode());
            if(previousGame.isPresent()) {
                if(game.isPlayed()) {
                    game = this.mergePlayedGameWithExistingGame(game,previousGame.get());
                } else {
                    game = this.mergeUnplayedGameWithExistingGame(game,previousGame.get());
                }
            }

            games.add(this.gameRepository.save(game));
        }
        return games;
    }

    private List<ImportCsvGameDTO> fileToImportCSVGameDTO(InputStream fileStream) throws FileException, FileDataException {
        List<List<String>> csvLines = this.parseToCSVLines(fileStream);
        if (csvLines.isEmpty()) {
            throw new FileDataException("csv file is empty");
        }

        List<String> header = csvLines.remove(0);
        if(!header.equals(headerPlayed) && !header.equals(headerNoPlayed) ) {
            throw new FileDataException("csv data header hasn't the required format");
        }

        List<ImportCsvGameDTO> importCSVGameDTOs = new ArrayList<>();
        for (List<String> csvLine:csvLines) {
            importCSVGameDTOs.add(new ImportCsvGameDTO(header,csvLine));
        }

        return importCSVGameDTOs;
    }


    private List<List<String>> parseToCSVLines(InputStream fileStream) throws FileDataException, FileException {

        try (InputStreamReader reader = new InputStreamReader(new BOMInputStream(fileStream))) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
                return new ArrayList<>(csvReader.readAll().stream().map(Arrays::asList).toList());
            }
        } catch (IOException ioe) {
            throw new FileException("file isn't readable ");
        } catch (com.opencsv.exceptions.CsvException e) {
            throw new FileDataException("file isn't in a CSV's format");
        }
    }

    private Game mergeUnplayedGameWithExistingGame(Game unplayedGame,Game existingGame) {
        var referees = RefereesBuilder.builder()
                .withDesignatedReferee1(existingGame.getReferees().designatedReferee1())
                .withDesignatedReferee2(existingGame.getReferees().designatedReferee2())
                .withOfficiatingReferee1(unplayedGame.getReferees().officiatingReferee1())
                .withOfficiatingReferee2(unplayedGame.getReferees().officiatingReferee2())
                .build();
        return GameBuilder.builder()
                .withCode(unplayedGame.getCode())
                .withDate(unplayedGame.getDate())
                .withTime(unplayedGame.getTime())
                .withDay(unplayedGame.getDay())
                .withSeason(unplayedGame.getSeason())
                .withWeek(unplayedGame.getWeek())
                .withCompetition(unplayedGame.getCompetition())
                .withHalle(unplayedGame.getHalle())
                .withHomeTeam(unplayedGame.getHomeTeam())
                .withVisitingTeam(unplayedGame.getVisitingTeam())
                .withScore(existingGame.getScore())
                .withReferees(referees)
                .withFDME(existingGame.getFdme())
                .build();
    }

    private Game mergePlayedGameWithExistingGame(Game playedGame,Game existingGame) {
        playedGame.getHomeTeam().setTeamsColor(existingGame.getHomeTeam().getTeamsColor());
        playedGame.getHomeTeam().setCoach(existingGame.getHomeTeam().getCoach());
        playedGame.getVisitingTeam().setTeamsColor(existingGame.getVisitingTeam().getTeamsColor());
        playedGame.getVisitingTeam().setCoach(existingGame.getVisitingTeam().getCoach());
        return GameBuilder.builder()
                .withCode(playedGame.getCode())
                .withDate(playedGame.getDate())
                .withTime(playedGame.getTime())
                .withDay(playedGame.getDay())
                .withSeason(playedGame.getSeason())
                .withWeek(playedGame.getWeek())
                .withCompetition(playedGame.getCompetition())
                .withHalle(existingGame.getHalle())
                .withHomeTeam(playedGame.getHomeTeam())
                .withVisitingTeam(playedGame.getVisitingTeam())
                .withScore(playedGame.getScore())
                .withReferees(playedGame.getReferees())
                .withFDME(playedGame.getFdme())
                .build();
    }
}
