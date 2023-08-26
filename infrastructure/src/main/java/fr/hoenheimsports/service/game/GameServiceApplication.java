package fr.hoenheimsports.service.game;

import fr.hoenheimsports.dto.game.create.GameCreateDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.api.GameDisplay;
import fr.hoenheimsports.gamedomain.api.GameImportFile;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.builder.TeamBuilder;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;
import fr.hoenheimsports.service.game.mapper.GameMapper;
import fr.hoenheimsports.service.game.mapper.GenderMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

@Service
public class GameServiceApplication {

    private final GameImportFile gameImportFile;
    private final GameDisplay gameDisplay;
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final ClubRepository clubRepository;
    private final TeamRepository teamRepository;
    private final HallRepository halleRepository;
    private final GameMapper gameMapper;
    private final GenderMapper genderMapper;



    public GameServiceApplication(GameImportFile gameImportFile, GameDisplay gameDisplay, GameRepository gameRepository, CategoryRepository categoryRepository, ClubRepository clubRepository, TeamRepository teamRepository, HallRepository halleRepository, GameMapper gameMapper, GenderMapper genderMapper) {
        this.gameImportFile = gameImportFile;
        this.gameDisplay = gameDisplay;
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.clubRepository = clubRepository;
        this.teamRepository = teamRepository;
        this.halleRepository = halleRepository;
        this.gameMapper = gameMapper;

        this.genderMapper = genderMapper;
    }


    public List<GameDTO> importFile(MultipartFile csvFile) throws FileException, FileDataException {
        InputStream inputStream = null;
        try {
            inputStream = csvFile.getInputStream();
        } catch (IOException ioe) {
            throw new FileException();
        }
        return this.gameImportFile.importFileGame(inputStream).stream()
                .map(this.gameMapper::gameToGameDTO)
                .toList();
    }

    public GameDTO createGame(GameCreateDTO gameCreateDTO) {
        Gender gender = this.genderMapper.mapGenderDTOToGender(gameCreateDTO.gender());
        Category category = this.categoryRepository.findByName(gameCreateDTO.categoryId()).orElseThrow();
        Club homeTeamClub =  this.clubRepository.findByCode(gameCreateDTO.homeTeamClubCode()).orElseThrow();
        Club visitingTeamClub =  this.clubRepository.findByCode(gameCreateDTO.visitingTeamClubCode()).orElseThrow();
        Team homeTeam = this.teamRepository.findTeamByKeys(homeTeamClub,gender,category,1).orElse(
                TeamBuilder.builder()
                        .withClub(homeTeamClub)
                        .withGender(gender)
                        .withCategory(category)
                        .withNumber(1)
                        .build()
        );
        Team visitingTeam = this.teamRepository.findTeamByKeys(visitingTeamClub,gender,category,1).orElse(
                TeamBuilder.builder()
                        .withClub(visitingTeamClub)
                        .withGender(gender)
                        .withCategory(category)
                        .withNumber(1)
                        .build()
        );
        Hall halle = this.halleRepository.findHallById(gameCreateDTO.halleId()).orElseThrow();
        Game game = GameBuilder.builder()
                .withHalle(halle)
                .withHomeTeam(homeTeam)
                .withVisitingTeam(visitingTeam)
                .withDate(gameCreateDTO.dateTime().toLocalDate())
                .withTime(gameCreateDTO.dateTime().toLocalTime())
                .build();
        return this.gameMapper.gameToGameDTO(this.gameRepository.save(game));
    }

    public List<GameDTO> displayGames() {
        return this.gameDisplay.findAllGame().stream().map(this.gameMapper::gameToGameDTO).sorted(Comparator.nullsLast(Comparator.comparing(GameDTO::dateTime,Comparator.nullsLast(Comparator.naturalOrder())))).toList();
    }

    public GameDTO displayGame(String code) {
        return this.gameDisplay.findByCode(code).map(gameMapper::gameToGameDTO).orElse(null);
    }
}
