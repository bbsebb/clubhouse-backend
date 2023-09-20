package fr.hoenheimsports.service.game;

import fr.hoenheimsports.dto.booking.filter.TimeslotFilterDTO;
import fr.hoenheimsports.dto.game.create.GameCreateDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.api.*;
import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;
import fr.hoenheimsports.service.game.mapper.GameMapper;
import fr.hoenheimsports.service.game.mapper.GenderMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class GameServiceApplication {

    private final CSVGameServiceApplication gameImportFile;
    private final GameCreate gameCreate;
    private final GameDisplay gameDisplay;
    private final TeamCreate teamCreate;
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final ClubRepository clubRepository;
    private final TeamRepository teamRepository;
    private final HallRepository halleRepository;
    private final GameMapper gameMapper;
    private final GenderMapper genderMapper;
    private final SeasonCreate seasonCreate;
    private final TeamUpdate teamUpdate;


    public GameServiceApplication(CSVGameServiceApplication gameImportFile, GameCreate gameCreate, GameDisplay gameDisplay, TeamCreate teamCreate, GameRepository gameRepository, CategoryRepository categoryRepository, ClubRepository clubRepository, TeamRepository teamRepository, HallRepository halleRepository, GameMapper gameMapper, GenderMapper genderMapper, SeasonCreate seasonCreate, TeamUpdate teamUpdate) {
        this.gameImportFile = gameImportFile;
        this.gameCreate = gameCreate;
        this.gameDisplay = gameDisplay;
        this.teamCreate = teamCreate;
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.clubRepository = clubRepository;
        this.teamRepository = teamRepository;
        this.halleRepository = halleRepository;
        this.gameMapper = gameMapper;

        this.genderMapper = genderMapper;
        this.seasonCreate = seasonCreate;
        this.teamUpdate = teamUpdate;
    }


    public List<GameDTO> importFile(MultipartFile csvFile) throws FileException, FileDataException, CoachNotFoundException, ClubNotFoundException, HallNotFoundException, TeamNotFoundException, GameNotFoundException {
        InputStream inputStream = null;
        try {
            inputStream = csvFile.getInputStream();
        } catch (IOException ioe) {
            throw new FileException();
        }
        return this.gameImportFile.importFile(inputStream).stream()
                .map(this.gameMapper::gameToGameDTO)
                .toList();
    }

    public GameDTO createGame(GameCreateDTO gameCreateDTO) throws TeamNotFoundException, GameAlreadyExistsException, CategoryNotFoundException, ClubNotFoundException {
        Pool pool = Pool.UNKNOWN;
        Season season = this.seasonCreate.create(LocalDate.now());
        Day day = Day.SINGLE_DAY_GAME;
        Week week = Week.NOW;
        Referees referees = Referees.UNKNOWN;
        Category category = this.categoryRepository.findById(gameCreateDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Gender gender = this.genderMapper.mapGenderDTOToGender(gameCreateDTO.gender());
        int number = 1; //TODO
        Club homeClub = this.clubRepository.findById(gameCreateDTO.homeTeamClubCode()).orElseThrow(ClubNotFoundException::new);
        TeamsColor teamsColor = TeamsColor.UNKNOWN;
        Coach coach = Coach.UNKNOWN;
        Team homeTeam = null;
        try {
            homeTeam = this.teamCreate.create(category,gender,number,homeClub,teamsColor,coach);
        } catch (TeamAlreadyExistsException e) {
            homeTeam = this.teamUpdate.update(category,gender,number,homeClub,teamsColor,coach);
        }

        Club visitingClub = this.clubRepository.findById(gameCreateDTO.visitingTeamClubCode()).orElseThrow(ClubNotFoundException::new);
        Team visitingTeam = null;
        try {
            visitingTeam = this.teamCreate.create(category,gender,number,visitingClub,teamsColor,coach);
        } catch (TeamAlreadyExistsException e) {
            visitingTeam = this.teamUpdate.update(category,gender,number,visitingClub,teamsColor,coach);
        }
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate date = gameCreateDTO.dateTime().toLocalDate();
        LocalTime time = gameCreateDTO.dateTime().toLocalTime();
        Game game = this.gameCreate.create(pool, season, day, week, Hall.UNKNOWN, referees, homeTeam, visitingTeam, score, fdme, date, time);
        return this.gameMapper.gameToGameDTO(game);
    }

    public List<GameDTO> displayGames() {
        return this.gameDisplay.findAllGame().stream().map(this.gameMapper::gameToGameDTO).sorted(Comparator.nullsLast(Comparator.comparing(GameDTO::dateTime,Comparator.nullsLast(Comparator.naturalOrder())))).toList();
    }
    public List<GameDTO> displayGames(TimeslotFilterDTO timeslotFilterDTO) {
        return this.gameDisplay.findAllGame()
                .stream()
                .map(this.gameMapper::gameToGameDTO)
                .filter(gameDTO -> gameDTO.dateTime() != null && gameDTO.dateTime().isAfter(timeslotFilterDTO.start()) && gameDTO.dateTime().isBefore(timeslotFilterDTO.end()))
                .filter(gameDTO -> gameDTO.halle().address().city().equalsIgnoreCase("HOENHEIM"))
                .sorted(Comparator.nullsLast(Comparator.comparing(GameDTO::dateTime,Comparator.nullsLast(Comparator.naturalOrder()))))
                .toList();
    }

    public GameDTO displayGame(String code) {
        return this.gameDisplay.findByCode(code).map(gameMapper::gameToGameDTO).orElse(null);
    }


}
