package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Day;
import fr.hoenheimsports.gamedomain.model.Game;
import fr.hoenheimsports.gamedomain.model.Gender;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;
import fr.hoenheimsports.gamedomain.spi.HalleRepository;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;
import fr.hoenheimsports.repository.entity.game.GameEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class GameMapperAdapter implements GameMapper{

    @Qualifier("GameMapperImpl")
    private final GameMapper gameMapper;
    private final HalleRepository halleRepository;
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final RefereeRepository refereeRepository;


    public GameMapperAdapter(GameMapper gameMapper, HalleRepository halleRepository, TeamRepository teamRepository, CoachRepository coachRepository, RefereeRepository refereeRepository) {
        this.gameMapper = gameMapper;
        this.halleRepository = halleRepository;
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
        this.refereeRepository = refereeRepository;
    }

    @Override
    public Game gameEntityToGame(GameEntity gameEntity) {
        return this.gameMapper.gameEntityToGame(gameEntity);
    }

    @Override
    public GameEntity gameToGameEntity(Game game) {
        return this.gameMapper.gameToGameEntity(game);
    }

    @Override
    public GameDTO gameToGameDTO(Game game) {
        return this.gameMapper.gameToGameDTO(game);
    }

    @Override
    public Game gameDTOToGame(GameDTO gameDTO) {
        Game game = GameBuilder.builder()
                .withCode(gameDTO.code())
                .withDate(gameDTO.dateTime().toLocalDate())
                .withTime(gameDTO.dateTime().toLocalTime())
                .withDay(Day.SINGLE_DAY_GAME)
                .withCompetition(Competition.UNKNOWN)
                .withHalle(halleBuilder -> halleBuilder
                        .addIdGeneratorFromRepository(this.halleRepository)
                        .withName(gameDTO.halle().name())
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet(gameDTO.halle().address().street())
                                .withPostalCode(gameDTO.halle().address().postalCode())
                                .withStreet(gameDTO.halle().address().city())))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .addIdGeneratorFromRepository(this.teamRepository)
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(gameDTO.homeTeam().club().code())
                                .withName(gameDTO.homeTeam().club().name()))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(gameDTO.homeTeam().category().age())
                                .withIsMaxAge(gameDTO.homeTeam().category().isMaxAge()))
                        .withGender(this.mapToGender(gameDTO.homeTeam().gender().name()))
                        .withNumber(gameDTO.homeTeam().number())
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withShirtColor1(gameDTO.homeTeam().teamsColor().shirtColor1())
                                .withShirtColor2(gameDTO.homeTeam().teamsColor().shirtColor2())
                                .withGoalkeeperColor1(gameDTO.homeTeam().teamsColor().goalkeeperColor1())
                                .withGoalkeeperColor2(gameDTO.homeTeam().teamsColor().goalkeeperColor2()))
                        .withCoach(coachBuilder -> coachBuilder
                                .addIdGeneratorFromRepository(this.coachRepository)
                                .withName(gameDTO.homeTeam().coach().name())
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber(gameDTO.homeTeam().coach().phoneNumber().phoneNumber()))))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .addIdGeneratorFromRepository(this.teamRepository)
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(gameDTO.visitingTeam().club().code())
                                .withName(gameDTO.visitingTeam().club().name()))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(gameDTO.visitingTeam().category().age())
                                .withIsMaxAge(gameDTO.visitingTeam().category().isMaxAge()))
                        .withGender(this.mapToGender(gameDTO.visitingTeam().gender().name()))
                        .withNumber(gameDTO.visitingTeam().number())
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withShirtColor1(gameDTO.visitingTeam().teamsColor().shirtColor1())
                                .withShirtColor2(gameDTO.visitingTeam().teamsColor().shirtColor2())
                                .withGoalkeeperColor1(gameDTO.visitingTeam().teamsColor().goalkeeperColor1())
                                .withGoalkeeperColor2(gameDTO.visitingTeam().teamsColor().goalkeeperColor2()))
                        .withCoach(coachBuilder -> coachBuilder
                                .addIdGeneratorFromRepository(this.coachRepository)
                                .withName(gameDTO.visitingTeam().coach().name())
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber(gameDTO.visitingTeam().coach().phoneNumber().phoneNumber()))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(gameDTO.score().homeScore())
                        .withVisitingScore(gameDTO.score().visitingScore()))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().designatedReferee1().name()))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().designatedReferee2().name()))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().officiatingReferee1().name()))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().officiatingReferee2().name())))
                .withFDME(fdmeBuilder -> fdmeBuilder
                        .withUrl(gameDTO.fdme().url()))
                .build();
        return game;
    }

    private Gender mapToGender(String numPool) {
        char genderStr;
        try {
            genderStr = numPool.charAt(0);
        } catch (IndexOutOfBoundsException iobe) {
            genderStr = 'U';
        }
        //Z est considéré comme mixte, mais en réalité, les équipes sont nommées M à chaque fois.
        return switch (genderStr) {
            case 'Z' -> Gender.MIXED;
            case 'F' -> Gender.FEMALE;
            case 'M' -> Gender.MALE;
            default -> Gender.UNKNOWN;
        };
    }
}
