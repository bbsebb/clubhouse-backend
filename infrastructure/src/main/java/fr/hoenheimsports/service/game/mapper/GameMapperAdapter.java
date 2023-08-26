package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.dto.game.view.GameDTO;
import fr.hoenheimsports.gamedomain.builder.*;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;
import fr.hoenheimsports.gamedomain.spi.HallRepository;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;
import fr.hoenheimsports.repository.game.entity.game.GameEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Primary
@Service
public class GameMapperAdapter implements GameMapper{

    @Qualifier("GameMapperImpl")
    private final GameMapper gameMapper;
    private final HallRepository halleRepository;
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final RefereeRepository refereeRepository;


    public GameMapperAdapter(GameMapper gameMapper, HallRepository halleRepository, TeamRepository teamRepository, CoachRepository coachRepository, RefereeRepository refereeRepository) {
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
        return GameBuilder.builder()
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
    @Override
    public Game importCsvGameDTOToGame(ImportCsvGameDTO importCsvGameDTO) throws FileDataException {
        var codeGame = importCsvGameDTO.getCodeRenc();

        var scoreHomeTeam = importCsvGameDTO.getScRec();
        var scoreVisitingTeam = importCsvGameDTO.getScVis();
        var score = mapToScore(scoreHomeTeam, scoreVisitingTeam);

        var date = mapToLocalDate(importCsvGameDTO.getLe());

        var time = mapToLocalTime(importCsvGameDTO.getHoraire());

        var numDay = mapToDay(importCsvGameDTO.getJ());
        var day = DayBuilder.builder().withNumber(numDay).build();

        var season = mapToSeason(date);

        var year = mapToYear(importCsvGameDTO.getSemaine());
        var numWeek = mapToWeek(importCsvGameDTO.getSemaine());
        var week = WeekBuilder.builder()
                .withYear(year)
                .withWeek(numWeek)
                .build();

        var nameCompetition = importCsvGameDTO.getCompetition();
        var namePool = importCsvGameDTO.getPoule();
        var numPool = importCsvGameDTO.getNumPoule();
        var pool = PoolBuilder.builder()
                .withName(namePool)
                .withCode(numPool).build();
        var competition = CompetitionBuilder.builder()
                .withName(nameCompetition)
                .withPool(pool).build();

        var halleName = importCsvGameDTO.getNomSalle();
        var halleAddress = importCsvGameDTO.getAdresseSalle();
        var cp = importCsvGameDTO.getCP();
        var halleCity = importCsvGameDTO.getVille();
        var halleGlue = importCsvGameDTO.getColle();
        var halle = mapToHall(halleName, halleAddress, cp, halleCity, halleGlue);

        var homeTeamNumClub = importCsvGameDTO.getNumRec();
        var fullNameHomeTeam = importCsvGameDTO.getClubRec();
        var nameHomeTeam = ParserInfoTeam.mapToClubName(fullNameHomeTeam);
        var homeTeamClub = ClubBuilder.builder()
                .withCode(homeTeamNumClub)
                .withName(nameHomeTeam)
                .addHalle(halle).build();
        var categoryAgeHomeTeam = ParserInfoTeam.mapToCategoryAge(fullNameHomeTeam);
        var categoryIsMaxAgeHomeTeam = ParserInfoTeam.mapToCategoryIsMaxAge(fullNameHomeTeam);
        var homeTeamCategory = CategoryBuilder.builder()
                .withAge(categoryAgeHomeTeam)
                .withIsMaxAge(categoryIsMaxAgeHomeTeam).build();
        var gender = mapToGender(numPool);
        var numberHomeTeam = ParserInfoTeam.mapToTeamNumber(fullNameHomeTeam);
        var homeTeamColor = importCsvGameDTO.getCoulRec();
        var homeTeamGoalkeeperColor = importCsvGameDTO.getCoulGardRec();
        var homeTeamColors = ParserTeamColor.mapToTeamsColor(homeTeamColor, homeTeamGoalkeeperColor);
        var homeTeamCoachName = importCsvGameDTO.getEntRec();
        var homeTeamCoachTel = importCsvGameDTO.getTelEntRec();
        var homeTeamCoach = mapToCoach(homeTeamCoachName, homeTeamCoachTel);
        var homeTeam = TeamBuilder.builder()
                .addIdGeneratorFromRepository(this.teamRepository)
                .withClub(homeTeamClub)
                .withCategory(homeTeamCategory)
                .withGender(gender)
                .withNumber(numberHomeTeam)
                .withTeamsColor(homeTeamColors)
                .withCoach(homeTeamCoach).build();

        var visitingTeamNumClub = importCsvGameDTO.getNumVis();
        var fullNameVisitingTeam = importCsvGameDTO.getClubVis();
        var nameVisitingTeam = ParserInfoTeam.mapToClubName(fullNameVisitingTeam);
        var visitingTeamClub = ClubBuilder.builder()
                .withCode(visitingTeamNumClub)
                .withName(nameVisitingTeam)
                .build();
        var categoryAgeVisitingTeam = ParserInfoTeam.mapToCategoryAge(fullNameVisitingTeam);
        var categoryIsMaxAgeVisitingTeam = ParserInfoTeam.mapToCategoryIsMaxAge(fullNameVisitingTeam);
        var visitingTeamCategory = CategoryBuilder.builder()
                .withAge(categoryAgeVisitingTeam)
                .withIsMaxAge(categoryIsMaxAgeVisitingTeam)
                .build();
        var numberVisitingTeam = ParserInfoTeam.mapToTeamNumber(fullNameVisitingTeam);
        var visitingTeamColor = importCsvGameDTO.getCoulVis();
        var visitingTeamGoalkeeperColor = importCsvGameDTO.getCoulGardVis();
        var visitingTeamColors = ParserTeamColor.mapToTeamsColor(visitingTeamColor, visitingTeamGoalkeeperColor);
        var visitingTeamNameCoach = importCsvGameDTO.getEntVis();
        var visitingTeamTelCoach = importCsvGameDTO.getTelEntVis();
        var visitingTeamCoach = mapToCoach(visitingTeamNameCoach, visitingTeamTelCoach);
        var visitingTeam = TeamBuilder.builder()
                .addIdGeneratorFromRepository(this.teamRepository)
                .withClub(visitingTeamClub)
                .withCategory(visitingTeamCategory)
                .withGender(gender)
                .withNumber(numberVisitingTeam)
                .withTeamsColor(visitingTeamColors)
                .withCoach(visitingTeamCoach)
                .build();

        var designatedReferee1 = mapToReferee(importCsvGameDTO.getArb1Designe());
        var designatedReferee2 = mapToReferee(importCsvGameDTO.getArb2Designe());
        var officiatingReferee1 = mapToReferee(importCsvGameDTO.getArb1Sifle());
        var officiatingReferee2 = mapToReferee(importCsvGameDTO.getArb2Sifle());


        var referees = RefereesBuilder.builder()
                .withDesignatedReferee1(designatedReferee1)
                .withDesignatedReferee2(designatedReferee2)
                .withOfficiatingReferee1(officiatingReferee1)
                .withOfficiatingReferee2(officiatingReferee2)
                .build();

        var fdme = mapToFDME(importCsvGameDTO.getFDME());
        return  GameBuilder.builder()
                .withCode(codeGame)
                .withDate(date)
                .withTime(time)
                .withDay(day)
                .withSeason(season)
                .withWeek(week)
                .withCompetition(competition)
                .withHalle(halle)
                .withHomeTeam(homeTeam)
                .withVisitingTeam(visitingTeam)
                .withScore(score)
                .withReferees(referees)
                .withFDME(fdme)
                .build();
    }

    private FDME mapToFDME(String url) {
        if (url == null) {
            return FDME.UNKNOWN;
        } else {
            return FDMEBuilder.builder().withUrl(url).build();
        }
    }

    private Referee mapToReferee(String name) {
        if (name == null) {
            return Referee.UNKNOWN;
        } else {
            return RefereeBuilder.builder()
                    .addIdGeneratorFromRepository(this.refereeRepository)
                    .withName(name)
                    .build();
        }
    }

    private Coach mapToCoach(String name, String phoneNumber) {
        if (name == null) {
            return Coach.UNKNOWN;
        } else {
            return CoachBuilder.builder()
                    .addIdGeneratorFromRepository(this.coachRepository)
                    .withName(name)
                    .withPhoneNumber(mapToPhoneNumber(phoneNumber))
                    .build();
        }
    }

    private PhoneNumber mapToPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return PhoneNumber.UNKNOWN;
        } else {
            return new PhoneNumber(phoneNumber);
        }
    }

    private Season mapToSeason(LocalDate le) {
        // If there is no date, the season is le season from today
        le = (le == null) ?LocalDate.now():le;
        int startMonth = 8;
        int startDay = 1;
        LocalDate seasonStartDate = LocalDate.of(le.getYear(), startMonth, startDay);
        LocalDate seasonEndDate;

        if(le.isAfter(seasonStartDate) || le.isEqual(seasonStartDate)) {
            seasonEndDate = seasonStartDate.plusYears(1).minusDays(1);
        } else {
            seasonEndDate = seasonStartDate.minusDays(1);
            seasonStartDate = seasonStartDate.minusYears(1);
        }
        String name = "SEASON_" + seasonStartDate.getYear() + "_" + seasonEndDate.getYear();
        return new Season(name,seasonStartDate,seasonEndDate);
    }

    private int mapToWeek(String semaine) throws FileDataException {
        try {
            String[] parties = semaine.split("-");
            return Integer.parseInt(parties[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FileDataException("semaine shoud be like '2022-23'");
        }
    }

    private int mapToYear(String semaine) throws FileDataException {
        try {
            String[] parties = semaine.split("-");
            return Integer.parseInt(parties[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FileDataException("semaine shoud be like '2022-23'");
        }
    }
    private LocalDate mapToLocalDate(String le) {
        LocalDate date = null;
        if (le != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(le, formatter);
            }
        }
        return date;
    }

    private LocalTime mapToLocalTime(String horaire) {
        LocalTime time = null;
        if (horaire != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                time = LocalTime.parse(horaire, formatter);
            }
        }
        return time;
    }

    private int mapToDay(String dayStr) throws FileDataException {
        try {
            return Integer.parseInt(dayStr);
        } catch (NumberFormatException nfe) {
            throw new FileDataException("csv column day should be a integer");
        }
    }

    private Hall mapToHall(String name, String address, String cpStr, String city, String glue) throws FileDataException {
        if (name == null) {
            return Hall.UNKNOWN;
        } else if (address == null || cpStr == null || city == null) {
            return HalleBuilder.builder().withAddress(Address.UNKNOWN).build();
        } else {
            try {
                GlueAuthorization glueAuthorization;
                if (glue == null) {
                    glueAuthorization = GlueAuthorization.UNKNOWN;
                } else if (glue.equals("Toutes colles interdites")) {
                    glueAuthorization = GlueAuthorization.UNAUTHORIZED;
                } else if (glue.equals("Colle lavable à l'eau uniquement") || glue.equals("Colle fournie par le club recevant")) {
                    glueAuthorization = GlueAuthorization.AUTHORIZED;
                } else {
                    glueAuthorization = GlueAuthorization.UNKNOWN;
                }
                int cp = Integer.parseInt(cpStr);
                return HalleBuilder.builder()
                        .addIdGeneratorFromRepository(this.halleRepository)
                        .withName(name)
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet(address)
                                .withPostalCode(cp)
                                .withCity(city))
                        .withGlueAuthorization(glueAuthorization)
                        .build();
            } catch (NumberFormatException nfe) {
                throw new FileDataException("csv column cp should be a integer");
            }
        }
    }



    private Score mapToScore(String scoreStrRec, String scoreStrVis) throws FileDataException {
        if (scoreStrRec == null || scoreStrVis == null) {
            return Score.DEFAULT;
        } else {

            try {
                int scoreRec = Integer.parseInt(scoreStrRec);
                int scoreVis = Integer.parseInt(scoreStrVis);
                return new Score(scoreRec, scoreVis);
            }catch (NumberFormatException nfe) {
                throw new FileDataException("csv columns score should be a integer");
            } catch (IllegalArgumentException iae) {
                throw new FileDataException(iae.getMessage());
            }
        }
    }



    private static class ParserInfoTeam {
        private final static String REGEX = "([-/a-zA-Z ]+)(?: (?:(?:[U\\-](\\d{2})[MF]|(?:SM|SF)|)(\\d?))?|(?>\\(.*\\))?$)";
        /*
            *** REGEX ***
            1 groupe : Le nom de l'équipe,
            2 groupe : catégorie ou null pour les séniors
            3 groupe : le numéro d'équipe au null

            Le groupe 1 est erroné si NOM CLUB SM (ou SF), mais il semble qu'il ait tjs un numéro d'équipe pour les séniors.

            Cas particulier, s'il n'y a pas de catégorie, c'est soit SF, soit SM et tjs l'équipe 1.

            ERREURS CONNUES :
            * les accents ne sont pas reconnus

         */
        private final static Pattern pattern = Pattern.compile(REGEX);

        static String mapToClubName(String club) throws FileDataException {
            checkClub(club);
            return parseInfoTeam(club).group(1).toUpperCase();
        }

        static int mapToCategoryAge(String club) throws FileDataException {
            checkClub(club);
            String category = parseInfoTeam(club).group(2);
            int categoryAge;
            if (category != null) {
                categoryAge = Integer.parseInt(category);
            } else {
                categoryAge = 18;
            }
            return categoryAge;
        }

        static boolean mapToCategoryIsMaxAge(String club) throws FileDataException {
            checkClub(club);
            String category = parseInfoTeam(club).group(2);
            boolean categoryIsMaxAge;
            categoryIsMaxAge = category != null;
            return categoryIsMaxAge;
        }

        static int mapToTeamNumber(String club) throws FileDataException {
            checkClub(club);
            String teamNumberStr = parseInfoTeam(club).group(3);
            int teamNumber = 1;
            if (teamNumberStr != null && !teamNumberStr.isBlank()) {
                try {
                    teamNumber = Integer.parseInt(teamNumberStr);
                } catch (NumberFormatException e) {
                    throw new FileDataException("csv columns club should contain a integer");
                }
            }
            return teamNumber;
        }

        private static void checkClub(String club) throws FileDataException {
            if (club == null || club.isBlank()) {
                throw new FileDataException("csv columns club shouldn't be null or empty ");
            }
        }

        private static Matcher parseInfoTeam(String club) throws FileDataException {
            Matcher matcher = pattern.matcher(club.trim());
            if (!matcher.matches()) {
                throw new FileDataException("csv columns club should have a club's name then a category or null the a number's team or null ");
            }
            return matcher;
        }
    }


    private static class ParserTeamColor {
        static TeamsColor mapToTeamsColor(String shirtColor, String goalkeeperColor) {
            try {
                return new TeamsColor(split(shirtColor)[0], split(shirtColor)[1], split(goalkeeperColor)[0], split(goalkeeperColor)[1]);
            } catch (FileDataException fde) {
                return new TeamsColor(TeamColor.UNKNOWN, TeamColor.UNKNOWN, TeamColor.UNKNOWN, TeamColor.UNKNOWN);
            }
        }

        static private TeamColor[] split(String colors) throws FileDataException {

            if (colors == null) {
                throw new FileDataException("csv column Coul. shouldn't be null or empty");
            }

            TeamColor[] teamsColor = new TeamColor[2];

            String[] colorsStr = colors.toUpperCase().split("-");

            if (colorsStr.length == 1 && !colorsStr[0].isBlank()) {

                teamsColor[0] = TeamColor.getByFrenchName(colorsStr[0]);
                teamsColor[1] = null;
            } else if (colorsStr.length == 2 && !colorsStr[0].isBlank() && !colorsStr[1].isBlank()) {
                teamsColor[0] = TeamColor.getByFrenchName(colorsStr[0]);
                teamsColor[1] = TeamColor.getByFrenchName(colorsStr[1]);
            } else {
                throw new FileDataException("csv column Coul. shouldn't contain 1 or 2 valid color");
            }

            return teamsColor;
        }
    }
}
