package fr.hoenheimsports.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import fr.hoenheimsports.gamedomain.builder.*;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.FileToGames;
import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CSVToGames implements FileToGames {
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

    @Override
    public List<Game> fileToGames(InputStream fileStream) throws FileDataException, FileException {

        List<List<String>> csvData = this.parseToList(fileStream);
        if (csvData.isEmpty()) {
            throw new FileDataException();
        }

        List<String> header = csvData.remove(0).equals(headerPlayed) ? headerPlayed : headerNoPlayed;
        List<CSVLine> csvDataWithHeader = new ArrayList<>();
        for (List<String> csvDataLine : csvData) {
            csvDataWithHeader.add(new CSVLine(header, csvDataLine));
        }
        List<Game> games = new ArrayList<>();
        for (CSVLine csvLine : csvDataWithHeader) {
            games.add(mapCSVLineToGame(csvLine));
        }
        return games;
    }

    private List<List<String>> parseToList(InputStream fileStream) throws FileDataException, FileException {

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


    private static Game mapCSVLineToGame(CSVLine csvLine) throws FileDataException {

        int day = mapToDay(csvLine.getJ());

        Gender gender = mapToGender(csvLine.getNumPoule());

        int numberHomeTeam = ParserInfoTeam.mapToTeamNumber(csvLine.getClubRec());
        String nameHomeTeam = ParserInfoTeam.mapToClubName(csvLine.getClubRec());
        String categoryHomeTeam = ParserInfoTeam.mapToCategoryName(csvLine.getClubRec());

        int numberVisitingTeam = ParserInfoTeam.mapToTeamNumber(csvLine.getClubVis());
        String nameVisitingTeam = ParserInfoTeam.mapToClubName(csvLine.getClubVis());
        String categoryVisitingTeam = ParserInfoTeam.mapToCategoryName(csvLine.getClubVis());

        Score score = mapToScore(csvLine.getScRec(), csvLine.getScVis());


        Halle halle = mapToHall(csvLine.getNomSalle(), csvLine.getAdresseSalle(), csvLine.getCP(), csvLine.getVille(), csvLine.getColle());
        return GameBuilder.builder()
                .withCode(csvLine.getCodeRenc())
                .withDate(mapToLocalDate(csvLine.getLe()))
                .withTime(mapToLocalTime(csvLine.getHoraire()))
                .withDay(dayBuilder -> dayBuilder.withNumber(day).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName(csvLine.getCompetition())
                        .withPool(poolBuilder -> poolBuilder
                                .withName(csvLine.getPoule())
                                .withCode(csvLine.getNumPoule())))
                .withHalle(halle)
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(csvLine.getNumRec())
                                .withName(nameHomeTeam))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName(categoryHomeTeam))
                        .withGender(gender)
                        .withNumber(numberHomeTeam)
                        .withTeamsColor(ParserTeamColor.mapToTeamsColor(csvLine.getCoulRec(), csvLine.getCoulGardRec()))
                        .withCoach(mapToCoach(csvLine.getEntRec(), csvLine.getTelEntRec())))
                .withVisitingTeam(teamBuilder -> teamBuilder
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(csvLine.getNumVis())
                                .withName(nameVisitingTeam))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName(categoryVisitingTeam))
                        .withGender(gender)
                        .withNumber(numberVisitingTeam)
                        .withTeamsColor(ParserTeamColor.mapToTeamsColor(csvLine.getCoulVis(), csvLine.getCoulGardVis()))
                        .withCoach(mapToCoach(csvLine.getEntVis(), csvLine.getTelEntVis())))
                .withScore(score)
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(mapToReferee(csvLine.getArb1Designe()))
                        .withDesignatedReferee2(mapToReferee(csvLine.getArb2Designe()))
                        .withOfficiatingReferee1(mapToReferee(csvLine.getArb1Sifle()))
                        .withOfficiatingReferee2(mapToReferee(csvLine.getArb2Sifle())))
                .withFDME(mapToFDME(csvLine.getFDME()))
                .build();
    }

    private static FDME mapToFDME(String url) {
        if (url == null) {
            return FDME.UNKNOWN;
        } else {
            return FDMEBuilder.builder().withUrl(url).build();
        }
    }

    private static Referee mapToReferee(String name) {
        if (name == null) {
            return Referee.UNKNOWN;
        } else {
            return RefereeBuilder.builder().withName(name).build();
        }
    }

    private static Coach mapToCoach(String name, String phoneNumber) {
        if (name == null) {
            return Coach.UNKNOWN;
        } else {
            return CoachBuilder.builder().withName(name).withPhoneNumber(mapToPhoneNumber(phoneNumber)).build();
        }
    }

    private static PhoneNumber mapToPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return PhoneNumber.UNKNOWN;
        } else {
            return new PhoneNumber(phoneNumber);
        }
    }

    private static LocalDate mapToLocalDate(String dateStr) {
        LocalDate date = null;
        if (dateStr != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dateStr, formatter);
            }
        }
        return date;
    }

    private static LocalTime mapToLocalTime(String timeStr) {
        LocalTime time = null;
        if (timeStr != null) {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                time = LocalTime.parse(timeStr, formatter);
            }
        }
        return time;
    }

    private static int mapToDay(String dayStr) throws FileDataException {
        try {
            return Integer.parseInt(dayStr);
        } catch (NumberFormatException nfe) {
            throw new FileDataException("csv column day should be a integer");
        }
    }

    private static Halle mapToHall(String name, String address, String cpStr, String city, String glue) throws FileDataException {
        if (name == null) {
            return Halle.UNKNOWN;
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

    private static Gender mapToGender(String numPool) throws FileDataException {
        char genderStr;
        try {
            genderStr = numPool.charAt(0);
        } catch (IndexOutOfBoundsException iobe) {
            throw new FileDataException("csv column should contain 1 character minimum  ");
        }
        //Z est considéré comme mixte, mais en réalité, les équipes sont nommées M à chaque fois.
        return switch (genderStr) {
            case 'Z' -> Gender.MIXED;
            case 'F' -> Gender.FEMALE;
            case 'M' -> Gender.MALE;
            default -> Gender.UNKNOWN;
        };
    }

    private static Score mapToScore(String scoreStrRec, String scoreStrVis) throws FileDataException {
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


    private static class CSVLine {


        private String numPoule;
        private String competition;
        private String poule;
        private String J;
        private String le;
        private String horaire;
        private String clubRec;
        private String clubVis;

        private String arb1Designe;
        private String arb2Designe;

        private String codeRenc;
        private String nomSalle;
        private String adresseSalle;
        private String CP;
        private String Ville;
        private String colle;
        private String CoulRec;
        private String CoulGardRec;
        private String CoulVis;
        private String CoulGardVis;
        private String EntRec;
        private String TelEntRec;
        private String NumRec;
        private String NumVis;
        private String scRec;
        private String scVis;
        private String fdme;
        private String arb1Sifle;
        private String arb2Sifle;
        private String EntVis;
        private String TelEntVis;
/*
        variable doesn't use :
        private String semaine;
        private String CorrespRec;
        private String TelCorrespRec;
        private String CorrespVis;
        private String TelCorrespVis;
        private String fdmeRec;
        private String fdmeVis;
        private String penRec;
        private String penVis;
        private String forfRec;
        private String forfVis;
        private String secretaire;
        private String chronometreur;
        private String respSalle;
        private String tuteurTable;
        private String Etat;
        private String DateArrivee;
        private String observateur;
        private String delegue;
        private String clubHote;

       */

        public CSVLine(List<String> headers, List<String> csvLine) throws FileDataException {
            if (headers.size() != csvLine.size()) {
                throw new FileDataException();
            }
            int colonne = 0;
            for (String header : headers) {
                this.assignHeaderNameToCellValue(header, csvLine.get(colonne));
                colonne++;
            }
        }


        private void assignHeaderNameToCellValue(String headerName, String cellValue) throws FileDataException {
            cellValue = cellValue.trim().isEmpty() ? null : cellValue;
            switch (headerName) {

                case "num poule" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'num poule' column is empty and should be required");
                    }
                    this.numPoule = cellValue;
                }
                case "competition" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'competition' column is empty and should be required");
                    }
                    this.competition = cellValue;
                }
                case "poule" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'poule' column is empty and should be required");
                    }
                    this.poule = cellValue;
                }
                case "J" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'J' column is empty and should be required");
                    }
                    this.J = cellValue;
                }
                case "le" -> this.le = cellValue;
                case "horaire" -> this.horaire = cellValue;
                case "club rec" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'club rec' column is empty and should be required");
                    }
                    this.clubRec = cellValue;
                }
                case "club vis" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'num poule' column is empty and should be required");
                    }
                    this.clubVis = cellValue;
                }

                case "arb1 designe" -> this.arb1Designe = cellValue;
                case "arb2 designe" -> this.arb2Designe = cellValue;

                case "code renc" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'code renc' column is empty and should be required");
                    }
                    this.codeRenc = cellValue;
                }
                case "nom salle" -> this.nomSalle = cellValue;
                case "adresse salle" -> this.adresseSalle = cellValue;
                case "CP" -> this.CP = cellValue;
                case "Ville" -> this.Ville = cellValue;
                case "colle" -> this.colle = cellValue;
                case "Coul. Rec" -> this.CoulRec = cellValue;
                case "Coul. Gard. Rec" -> this.CoulGardRec = cellValue;
                case "Coul. Vis" -> this.CoulVis = cellValue;
                case "Coul. Gard. Vis" -> this.CoulGardVis = cellValue;
                case "Ent. Rec" -> this.EntRec = cellValue;
                case "Tel Ent. Rec" -> this.TelEntRec = cellValue;
                case "Ent. Vis" -> this.EntVis = cellValue;
                case "Tel Ent. Vis" -> this.TelEntVis = cellValue;
                case "Num rec" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'Num rec' column is empty and should be required");
                    }
                    this.NumRec = cellValue;
                }
                case "Num vis" -> {
                    if (cellValue == null) {
                        throw new FileDataException("'Num vis' column is empty and should be required");
                    }
                    this.NumVis = cellValue;
                }
                case "FDME" -> this.fdme = cellValue;
                case "sc rec" -> this.scRec = cellValue;
                case "sc vis" -> this.scVis = cellValue;

                case "arb1 sifle" -> this.arb1Sifle = cellValue;
                case "arb2 sifle" -> this.arb2Sifle = cellValue;
/*
                case "semaine" -> {
                    if(cellValue == null) {
                        throw new FileDataException();
                    }
                    this.semaine = cellValue;
                }
                case "fdme rec" -> this.fdmeRec = cellValue;
                case "club hote" -> this.clubHote = cellValue;
                case "observateur" -> this.observateur = cellValue;
                case "delegue" -> this.delegue = cellValue;
                case "Corresp. Rec" -> this.CorrespRec = cellValue;
                case "Tel Corresp. Rec" -> this.TelCorrespRec = cellValue;
                case "fdme vis" -> this.fdmeVis = cellValue;
                case "pen. rec" -> this.penRec = cellValue;
                case "pen. vis" -> this.penVis = cellValue;
                case "forf. rec" -> this.forfRec = cellValue;
                case "forf. vis" -> this.forfVis = cellValue;
                case "Corresp. Vis" -> this.CorrespVis = cellValue;
                case "Tel Corresp. Vis" -> this.TelCorrespVis = cellValue;
                case "secretaire" -> this.secretaire = cellValue;
                case "chronometreur" -> this.chronometreur = cellValue;
                case "resp salle" -> this.respSalle = cellValue;
                case "tuteur table" -> this.tuteurTable = cellValue;
                case "Etat" -> this.Etat = cellValue;
                case "Date Arrivee" -> this.DateArrivee = cellValue;*/
            }
        }


        public String getNumPoule() {
            return numPoule;
        }

        public String getCompetition() {
            return competition;
        }

        public String getPoule() {
            return poule;
        }

        public String getJ() {
            return J;
        }

        public String getLe() {
            return le;
        }

        public String getHoraire() {
            return horaire;
        }

        public String getClubRec() {
            return clubRec;
        }

        public String getClubVis() {
            return clubVis;
        }


        public String getArb1Designe() {
            return arb1Designe;
        }

        public String getArb2Designe() {
            return arb2Designe;
        }

        public String getCodeRenc() {
            return codeRenc;
        }

        public String getNomSalle() {
            return nomSalle;
        }

        public String getAdresseSalle() {
            return adresseSalle;
        }

        public String getCP() {
            return CP;
        }

        public String getVille() {
            return Ville;
        }

        public String getColle() {
            return colle;
        }

        public String getCoulRec() {
            return CoulRec;
        }

        public String getCoulGardRec() {
            return CoulGardRec;
        }

        public String getCoulVis() {
            return CoulVis;
        }

        public String getCoulGardVis() {
            return CoulGardVis;
        }

        public String getEntRec() {
            return EntRec;
        }

        public String getTelEntRec() {
            return TelEntRec;
        }


        public String getEntVis() {
            return EntVis;
        }

        public String getTelEntVis() {
            return TelEntVis;
        }


        public String getNumRec() {
            return NumRec;
        }

        public String getNumVis() {
            return NumVis;
        }

        public String getScRec() {
            return scRec;
        }

        public String getScVis() {
            return scVis;
        }

        public String getFDME() {
            return fdme;
        }


        public String getArb1Sifle() {
            return arb1Sifle;
        }

        public String getArb2Sifle() {
            return arb2Sifle;
        }

    }

    private static class ParserInfoTeam {
        private final static String REGEX = "([-/a-zA-Z ]+)(?: (?:(?:[U\\-](\\d{2})[MF]|(?:SM|SF)|)(\\d?))?|(?>\\(.*\\))?$)";
        /*
            *** REGEX ***
            1 groupe : Le nom de l'équipe,
            2 groupe : catégorie ou null pour les séniors
            3 groupe : le numéro d'équipe au null

            Le groupe 1 est eronné si NOM CLUB SM (ou SF), mais il semble qu'il ait tjs un numéro d'équipe pour les séniors.

            Cas particulier, s'il n'y a pas de catégorie, c'est soit SF, soit SM et tjs l'équipe 1.

         */
        private final static Pattern pattern = Pattern.compile(REGEX);

        static String mapToClubName(String club) throws FileDataException {
            checkClub(club);
            return parseInfoTeam(club).group(1);
        }

        static String mapToCategoryName(String club) throws FileDataException {
            checkClub(club);
            String category = parseInfoTeam(club).group(2);
            String categoryName;
            if (category != null) {
                categoryName = "moins de " + category + " ans";
            } else {
                categoryName = "senior";
            }
            return categoryName;
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

