package fr.hoenheimsports.service.game;

import fr.hoenheimsports.controller.game.GameController;
import fr.hoenheimsports.dto.game.importFromCsv.ImportCsvGameDTO;
import fr.hoenheimsports.gamedomain.api.*;
import fr.hoenheimsports.gamedomain.exception.*;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.service.game.mapper.CSVFileMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CSVGameServiceApplication implements FileToGames {
    private final CSVFileMapper csvFileMapper;
    private final GameCreate gameCreate;
    private final PoolCreate poolCreate;
    private final CompetitionCreate competitionCreate;
    private final SeasonCreate seasonCreate;
    private final HallCreate hallCreate;
    private final RefereeCreate refereeCreate;
    private final TeamCreate teamCreate;
    private final CategoryCreate categoryCreate;
    private final ClubCreate clubCreate;
    private final CoachCreate coachCreate;
    private final HallUpdate hallUpdate;
    private final ClubUpdate clubUpdate;
    private final CoachUpdate coachUpdate;
    private final TeamUpdate teamUpdate;
    private final GameUpdate gameUpdate;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);


    public CSVGameServiceApplication(CSVFileMapper csvFileMapper, GameCreate gameCreate, PoolCreate poolCreate, CompetitionCreate competitionCreate, SeasonCreate seasonCreate, HallCreate hallCreate, RefereeCreate refereeCreate, TeamCreate teamCreate, CategoryCreate categoryCreate, ClubCreate clubCreate, CoachCreate coachCreate, HallUpdate hallUpdate, ClubUpdate clubUpdate, CoachUpdate coachUpdate, TeamUpdate teamUpdate, GameUpdate gameUpdate) {
        this.csvFileMapper = csvFileMapper;
        this.gameCreate = gameCreate;
        this.poolCreate = poolCreate;
        this.competitionCreate = competitionCreate;
        this.seasonCreate = seasonCreate;
        this.hallCreate = hallCreate;
        this.refereeCreate = refereeCreate;
        this.teamCreate = teamCreate;
        this.categoryCreate = categoryCreate;
        this.clubCreate = clubCreate;
        this.coachCreate = coachCreate;
        this.hallUpdate = hallUpdate;
        this.clubUpdate = clubUpdate;
        this.coachUpdate = coachUpdate;
        this.teamUpdate = teamUpdate;

        this.gameUpdate = gameUpdate;
    }

    @Transactional
    public List<Game> importFile(InputStream fileStream) throws FileException, FileDataException, ClubNotFoundException, HallNotFoundException, TeamNotFoundException, GameNotFoundException, CoachNotFoundException {
        List<Game> importedGames = new ArrayList<>();
        List<ImportCsvGameDTO> importCsvGameDTOList = this.csvFileMapper.toImportCSVGameDTO(fileStream);
        for (ImportCsvGameDTO importCsvGameDTO : importCsvGameDTOList) {
            importedGames.add(this.createGame(importCsvGameDTO));
        }
        return importedGames;
    }

    private Game createGame(ImportCsvGameDTO importCsvGameDTO) throws FileDataException, HallNotFoundException, ClubNotFoundException, CoachNotFoundException, TeamNotFoundException, GameNotFoundException {

            String code = importCsvGameDTO.getCodeRenc();
            Competition competition = this.competitionCreate.create(importCsvGameDTO.getCompetition());
            Pool pool = this.poolCreate.create(importCsvGameDTO.getNumPoule(),importCsvGameDTO.getPoule(),competition);
            Season season = this.seasonCreate.create(this.toLocalDate(importCsvGameDTO.getLe()));
            Day day = new Day(this.toDay(importCsvGameDTO.getJ()));
            Week week = this.toWeek(importCsvGameDTO.getSemaine());
            Hall hall = this.toHall(importCsvGameDTO.getNomSalle(),importCsvGameDTO.getAdresseSalle(),importCsvGameDTO.getCP(),importCsvGameDTO.getVille(),importCsvGameDTO.getColle());
            Referee designatedReferee1 = this.toReferee(importCsvGameDTO.getArb1Designe());
            Referee designatedReferee2= this.toReferee(importCsvGameDTO.getArb2Designe());
            Referee officiatingReferee1= this.toReferee(importCsvGameDTO.getArb1Sifle());
            Referee officiatingReferee2= this.toReferee(importCsvGameDTO.getArb2Sifle());
            Referees referees = new Referees(designatedReferee1, designatedReferee2, officiatingReferee1, officiatingReferee2);
            Category category = this.categoryCreate.create(ParserInfoTeam.toCategoryAge(importCsvGameDTO.getClubRec()), ParserInfoTeam.toCategoryIsMaxAge(importCsvGameDTO.getClubRec()));
            Gender gender = this.toGender(importCsvGameDTO.getNumPoule());
            int numberHomeTeam = ParserInfoTeam.toTeamNumber(importCsvGameDTO.getClubRec());
        Club clubHomeTeam;
        try {
            clubHomeTeam = this.clubCreate.create(importCsvGameDTO.getNumRec(), ParserInfoTeam.mapToClubName(importCsvGameDTO.getClubRec()),hall);
        } catch (ClubAlreadyExistsException e) {
            clubHomeTeam = this.clubUpdate.update(importCsvGameDTO.getNumRec(), ParserInfoTeam.mapToClubName(importCsvGameDTO.getClubRec()),hall);
        }
        TeamsColor teamsColorHomeTeam = ParserTeamColor.mapToTeamsColor(importCsvGameDTO.getCoulRec(), importCsvGameDTO.getCoulGardRec());
            Coach coachHomeTeam = this.toCoach(importCsvGameDTO.getEntRec(),importCsvGameDTO.getTelEntRec());
        Team homeTeam;
        try {
            homeTeam = this.teamCreate.create(category,gender,numberHomeTeam,clubHomeTeam,teamsColorHomeTeam,coachHomeTeam);
        } catch (TeamAlreadyExistsException e) {
            homeTeam = this.teamUpdate.update(category,gender,numberHomeTeam,clubHomeTeam,teamsColorHomeTeam,coachHomeTeam);
        }
        int numberVisitingTeam = ParserInfoTeam.toTeamNumber(importCsvGameDTO.getClubVis());
        Club clubVisitingTeam;
        try {
            clubVisitingTeam = this.clubCreate.create(importCsvGameDTO.getNumVis(), ParserInfoTeam.mapToClubName(importCsvGameDTO.getClubVis()));
        } catch (ClubAlreadyExistsException e) {
            clubVisitingTeam = this.clubUpdate.update(importCsvGameDTO.getNumVis(), ParserInfoTeam.mapToClubName(importCsvGameDTO.getClubVis()));
        }
        TeamsColor teamsColorVisitingTeam = ParserTeamColor.mapToTeamsColor(importCsvGameDTO.getCoulVis(), importCsvGameDTO.getCoulGardVis());
            Coach coachVisitingTeam = this.toCoach(importCsvGameDTO.getEntVis(),importCsvGameDTO.getTelEntVis());
        Team visitingTeam;
        try {
            visitingTeam = this.teamCreate.create(category,gender,numberVisitingTeam,clubVisitingTeam,teamsColorVisitingTeam,coachVisitingTeam);
        } catch (TeamAlreadyExistsException e) {
            visitingTeam = this.teamUpdate.update(category,gender,numberVisitingTeam,clubVisitingTeam,teamsColorVisitingTeam,coachVisitingTeam);
        }
        Score score = this.toScore(importCsvGameDTO.getScRec(),importCsvGameDTO.getScVis());
            FDME fdme = this.toFDME(importCsvGameDTO.getFDME());
            LocalDate date = this.toLocalDate(importCsvGameDTO.getLe());
            LocalTime time = this.toLocalTime(importCsvGameDTO.getHoraire());
        try {
           return this.gameCreate.create(code, pool, season, day, week, hall, referees, homeTeam, visitingTeam, score, fdme, date, time);
        } catch (GameAlreadyExistsException e) {
            return this.gameUpdate.updateGame(code, pool, season, day, week, hall, referees, homeTeam, visitingTeam, score, fdme, date, time);
        }
    }

    private LocalDate toLocalDate(String le) throws FileDataException {
        LocalDate date = null;
        if (le != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    date = LocalDate.parse(le, formatter);
                } catch (DateTimeParseException e) {
                    throw new FileDataException("csv column horaire is %s and should be a dd/MM/yyyy".formatted(le));
                }

            }
        }
        return date;
    }

    private LocalTime toLocalTime(String horaire) throws FileDataException {
        LocalTime time = null;
        if (horaire != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                try {
                    time = LocalTime.parse(horaire, formatter);
                } catch (DateTimeParseException e) {
                    throw new FileDataException("csv column horaire is %s and should be a HH:mm:ss".formatted(horaire));
                }
            }
        }
        return time;
    }

    private int toDay(String dayStr) throws FileDataException {
        try {
            return Integer.parseInt(dayStr);
        } catch (NumberFormatException nfe) {
            throw new FileDataException("csv column day should be a integer");
        }
    }

    private Week toWeek(String semaine) throws FileDataException {
        try {
            String[] parties = semaine.split("-");
            Integer.parseInt(parties[1]);
            return new Week(Integer.parseInt(parties[0]),Integer.parseInt(parties[1]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FileDataException("semaine shoud be like '2022-23'");
        }
    }



    private Hall toHall(String name, String street, String cpStr, String city, String glue) throws FileDataException, HallNotFoundException {


        Address address;
        if(street == null || cpStr == null || city == null) {
            address = Address.UNKNOWN;
        } else {
            int cp;
            try {
                cp = Integer.parseInt(cpStr);
            } catch (NumberFormatException nfe) {
                throw new FileDataException("csv column cp should be a integer");
            }
            address = new Address(street, cp, city);
        }



        GlueAuthorization glueAuthorization;
        glue = (glue == null) ? "null" : glue;
        switch (glue) {
            case "Toutes colles interdites" -> glueAuthorization = GlueAuthorization.UNAUTHORIZED;
            case "Colle lavable à l'eau uniquement", "Colle fournie par le club recevant" -> glueAuthorization = GlueAuthorization.AUTHORIZED;
            default -> glueAuthorization = GlueAuthorization.UNKNOWN;
        }

        if(name == null) {
            return Hall.UNKNOWN;
        }
        try {
            return this.hallCreate.create(name, address, glueAuthorization);
        } catch (HallAlreadyExistsException e) {
            return this.hallUpdate.update(name, address, glueAuthorization);
        }

    }

    private Gender toGender(String numPool) {
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

    private Referee toReferee(String name)   {
        if (name == null) {
            return Referee.UNKNOWN;
        } else {
            return this.refereeCreate.create(name);
        }
    }

    private Coach toCoach(String name, String phoneNumber) throws CoachNotFoundException {
        if (name == null) {
            return Coach.UNKNOWN;
        } else {
            try {
                return this.coachCreate.create(name, toPhoneNumber(phoneNumber));
            } catch (CoachAlreadyExistsException e) {
                return this.coachUpdate.update(name, toPhoneNumber(phoneNumber));
            }
        }
    }

    private PhoneNumber toPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return PhoneNumber.UNKNOWN;
        } else {
            return new PhoneNumber(phoneNumber);
        }
    }

    private Score toScore(String scoreStrRec, String scoreStrVis) throws FileDataException {
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

    private FDME toFDME(String url) throws FileDataException {
        if (url == null) {
            return FDME.UNKNOWN;
        } else {
            try {
                return new FDME(URL.of(URI.create(url),null));
            } catch (MalformedURLException e) {
                throw new FileDataException("csv columns fmde should be a url");
            }
        }
    }

    private static class ParserInfoTeam {
        private final static String REGEX = "([-\\./a-zA-Z ]+?)(?:\\s?(?:[U\\-](\\d{1,2})[MF]|SM|SF)(\\d?)?)?$";
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

        static int toCategoryAge(String club) throws FileDataException {
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

        static boolean toCategoryIsMaxAge(String club) throws FileDataException {
            checkClub(club);
            String category = parseInfoTeam(club).group(2);
            boolean categoryIsMaxAge;
            categoryIsMaxAge = category != null;
            return categoryIsMaxAge;
        }

        static int toTeamNumber(String club) throws FileDataException {
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
                LOGGER.warn("error in parsing club : %s".formatted(club));
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
