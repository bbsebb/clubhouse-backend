package fr.hoenheimsports.service;

import fr.hoenheimsports.gamedomain.exception.FileDataException;
import fr.hoenheimsports.gamedomain.exception.FileException;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVToGamesTest {
    @Mock
    private CoachRepository coachRepository;
    @Mock
    private HalleRepository halleRepository;
    @Mock
    private RefereeRepository refereeRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private CSVToGames fileToGames;
    private static final String csvFileWithUnplayedGame = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
            2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
            2022-42;M50001301R;"coupe de france regionale masculine 2022-23";"1° TOUR";1;22/10/2022;19:00:00;MARLENHEIM;HOENHEIM;MARLENHEIM;"NOEL ARTHUR";;;;SAEHOBZ;"SALLE OMNISPORTS DE MARLENHEIM";"2 b, rue de l'usine";67520;MARLENHEIM;"Colle lavable à l'eau uniquement";Vert;Rose;;;"NIOGRET ALEXIS";+330663608784;"HELFFRICH PASCAL";+330620042137;;;;;5667036;5667028
            2022-42;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";6;22/10/2022;19:00:00;HOENHEIM;"DAMBACH LA VILLE";HOENHEIM;"RICO EMILIE";"PICHERY AURORE";;;SAEEHBY;"GYMNASE MUNICIPAL";"16 , rue des vosges";67800;HOENHEIM;"Colle fournie par le club recevant";Noir;Vert;Bordeaux;Jaune;"MALL JEAN-PHILIPPE";+330604650942;"MALL JEAN-PHILIPPE";+330604650942;"SARGENTON-CALLARD JEREMY";+330663815913;"FERLET PHILIPPE";+33618894896;5667028;5667101
                    """;
    private static final String csvFileWithPLayedGame = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"sc rec";"sc vis";"fdme rec";"fdme vis";"pen. rec";"pen. vis";"forf. rec";"forf. vis";"arb1 designe";"arb2 designe";"arb1 sifle";"arb2 sifle";secretaire;chronometreur;observateur;delegue;"resp salle";"tuteur table";"code renc";"Num rec";"Num vis";Etat;Forfait;Penalite;FDME;"Date Arrivee"
            2022-36;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";1;11/09/2022;16:00:00;"COLMAR CENTRE ALSACE";HOENHEIM;25;20;25;20;0;0;0;0;"MERCHDI BILEL";"MERCHDI ZAKARIA";"MERCHDI BILEL";"MERCHDI ZAKARIA";"MALL PHILIPPE";"GERMAIN PIERRE";"HOUPERT JEREMY";;"WACKENTHALER SABINE";;SAEEHAY;5668119;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEHAY_y9SFD6F15w6kgTJrCyho9g==.pdf;"11/09/2022 17:39"
            2022-37;M56670300G;"67-01 1ere division territoriale masculins";"67-01 DTM";1;16/09/2022;21:00:00;"STRASBOURG ASPTT SM1";"HOENHEIM SM2";34;28;34;28;0;0;0;0;"RAUSER ALAN";;"RAUSER ALAN";;"BURGMANN PHILIPPE";;;;;;SAEEPFO;5667006;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEPFO_NWx6azHAXlJoYKWre0iXyg==.pdf;"16/09/2022 22:30"
            2022-37;M56670200G;"67-03 3eme division territoriale masculins";"67-03 DTM PLE A";1;17/09/2022;19:00:00;"ALPHA HANDBALL SM2";"HOENHEIM SM3";37;20;37;20;0;0;0;0;"RAPPOLD JOEL";;"RAPPOLD JOEL";;"HEID STEPHANE";"WENDLING ANDRE";;;;;SAEEPWS;5667117;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEPWS_nN9iw7Umdd0mN7Zf0WwABQ==.pdf;"17/09/2022 21:07"
            2022-37;F56670001G;"67-11 1ere division territoriale feminines";"67-01 DTF";1;16/09/2022;21:00:00;"HOENHEIM SF2";"REICHSTETT SF3";34;23;34;23;0;0;0;0;"PETIT GUILLAUME";;"PETIT GUILLAUME";;;"ELCHINGER THOMAS";;;;;SAEEQIY;5667028;5667045;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEQIY_IJQY2iirvZue3g1vmAOOHA==.pdf;"16/09/2022 22:30"
                    """;

    @Test
    void fileToGamesWithHeaderNoPlayed() throws FileException, FileDataException {
        InputStream inputStreamCsvFile = new ByteArrayInputStream(csvFileWithUnplayedGame.getBytes());
        when(coachRepository.findCoachByKeys(any(String.class))).thenReturn(Optional.empty());
        when(halleRepository.findHallByKeys(any(String.class), any(String.class), any(Integer.class), any(String.class))).thenReturn(Optional.empty());
        when(refereeRepository.findRefereeByKeys(any(String.class))).thenReturn(Optional.empty());
        when(teamRepository.findTeamByKeys(any(Club.class), any(Gender.class), any(Category.class), any(Integer.class))).thenReturn(Optional.empty());
        List<Game> games = fileToGames.fileToGames(inputStreamCsvFile);
        assertEquals(3, games.size());
    }

    @Test
    void fileToGamesWithHeaderPlayed() throws FileException, FileDataException {

        InputStream inputStreamCsvFile = new ByteArrayInputStream(csvFileWithPLayedGame.getBytes());
        when(refereeRepository.findRefereeByKeys(any(String.class))).thenReturn(Optional.empty());
        when(teamRepository.findTeamByKeys(any(Club.class), any(Gender.class), any(Category.class), any(Integer.class))).thenReturn(Optional.empty());

        List<Game> games = fileToGames.fileToGames(inputStreamCsvFile);
        assertEquals(4, games.size());
    }

    @Test
    void fileToGamesWithBadHeader() {
        String csvBadHeaderFile = """
            semaine;"num poule";cometition;poule;J;le;horaire;"club rec";"club vis";"sc rec";"sc vis";"fdme rec";"fdme vis";"pen. rec";"pen. vis";"forf. rec";"forf. vis";"arb1 designe";"arb2 designe";"arb1 sifle";"arb2 sifle";secretaire;chronometreur;observateur;delegue;"resp salle";"tuteur table";"code renc";"Num rec";"Num vis";Etat;Forfait;Penalite;FDME;"Date Arrivee"
            2022-36;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";1;11/09/2022;16:00:00;"COLMAR CENTRE ALSACE";HOENHEIM;25;20;25;20;0;0;0;0;"MERCHDI BILEL";"MERCHDI ZAKARIA";"MERCHDI BILEL";"MERCHDI ZAKARIA";"MALL PHILIPPE";"GERMAIN PIERRE";"HOUPERT JEREMY";;"WACKENTHALER SABINE";;SAEEHAY;5668119;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEHAY_y9SFD6F15w6kgTJrCyho9g==.pdf;"11/09/2022 17:39"
            2022-37;M56670300G;"67-01 1ere division territoriale masculins";"67-01 DTM";1;16/09/2022;21:00:00;"STRASBOURG ASPTT SM1";"HOENHEIM SM2";34;28;34;28;0;0;0;0;"RAUSER ALAN";;"RAUSER ALAN";;"BURGMANN PHILIPPE";;;;;;SAEEPFO;5667006;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEPFO_NWx6azHAXlJoYKWre0iXyg==.pdf;"16/09/2022 22:30"
            2022-37;M56670200G;"67-03 3eme division territoriale masculins";"67-03 DTM PLE A";1;17/09/2022;19:00:00;"ALPHA HANDBALL SM2";"HOENHEIM SM3";37;20;37;20;0;0;0;0;"RAPPOLD JOEL";;"RAPPOLD JOEL";;"HEID STEPHANE";"WENDLING ANDRE";;;;;SAEEPWS;5667117;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEPWS_nN9iw7Umdd0mN7Zf0WwABQ==.pdf;"17/09/2022 21:07"
            2022-37;F56670001G;"67-11 1ere division territoriale feminines";"67-01 DTF";1;16/09/2022;21:00:00;"HOENHEIM SF2";"REICHSTETT SF3";34;23;34;23;0;0;0;0;"PETIT GUILLAUME";;"PETIT GUILLAUME";;;"ELCHINGER THOMAS";;;;;SAEEQIY;5667028;5667045;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEQIY_IJQY2iirvZue3g1vmAOOHA==.pdf;"16/09/2022 22:30"
                    """;
        InputStream inputStreamCsvFile3 = new ByteArrayInputStream(csvBadHeaderFile.getBytes());
        var exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamCsvFile3));
        String expectedMessage = "csv data header hasn't the required format";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void fileToGamesWithEmptyUnauthorizedCell() {

        String csvFileWithMissingSemaine = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                ;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                        """;
        InputStream inputStreamBadCsvFileNoPlayedSemaine = new ByteArrayInputStream(csvFileWithMissingSemaine.getBytes());
        var exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedSemaine));
        String expectedMessage = "'Semaine' column is empty and should be required";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingNumPool = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                        """;
        InputStream inputStreamBadCsvFileNoPlayedNumPool = new ByteArrayInputStream(csvFileWithMissingNumPool.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedNumPool));
        expectedMessage = "'num poule' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingCategorie = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;;"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedCompetition = new ByteArrayInputStream(csvFileWithMissingCategorie.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedCompetition));
        expectedMessage = "'competition' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingPoule = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";;1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedPool = new ByteArrayInputStream(csvFileWithMissingPoule.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedPool));
        expectedMessage = "'poule' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingJ = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedJ = new ByteArrayInputStream(csvFileWithMissingJ.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedJ));
        expectedMessage = "'J' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingClubRec = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;;"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedClubRec = new ByteArrayInputStream(csvFileWithMissingClubRec.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedClubRec));
        expectedMessage = "'club rec' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingClubVis = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";;;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedClubVis = new ByteArrayInputStream(csvFileWithMissingClubVis.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedClubVis));
        expectedMessage = "'club vis' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingCodeRen = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";;;;;;;;;;;;;;;;;;;5667095;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedCodeRenc = new ByteArrayInputStream(csvFileWithMissingCodeRen.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedCodeRenc));
        expectedMessage = "'code renc' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingNumRec = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;;5667028
                    """;
        InputStream inputStreamBadCsvFileNoPlayedNumRec = new ByteArrayInputStream(csvFileWithMissingNumRec.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedNumRec));
        expectedMessage = "'Num rec' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        String csvFileWithMissingNumVis = """
                semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
                2022-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;;;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";;;;" ";" ";SAEJPJW;;;;;;;;;;;;;;;;;;5667095;
                    """;
        InputStream inputStreamBadCsvFileNoPlayedNumVis = new ByteArrayInputStream(csvFileWithMissingNumVis.getBytes());
        exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamBadCsvFileNoPlayedNumVis));
        expectedMessage = "'Num vis' column is empty and should be required";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void fileToGamesWithBadSemaine() {
        String csvFileWithBadSemaine = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
            aa-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
            2022-bb;M50001301R;"coupe de france regionale masculine 2022-23";"1° TOUR";1;22/10/2022;19:00:00;MARLENHEIM;HOENHEIM;MARLENHEIM;"NOEL ARTHUR";;;;SAEHOBZ;"SALLE OMNISPORTS DE MARLENHEIM";"2 b, rue de l'usine";67520;MARLENHEIM;"Colle lavable à l'eau uniquement";Vert;Rose;;;"NIOGRET ALEXIS";+330663608784;"HELFFRICH PASCAL";+330620042137;;;;;5667036;5667028
            202260;M50001301R;"coupe de france regionale masculine 2022-23";"1° TOUR";1;22/10/2022;19:00:00;MARLENHEIM;HOENHEIM;MARLENHEIM;"NOEL ARTHUR";;;;SAEHOBZ;"SALLE OMNISPORTS DE MARLENHEIM";"2 b, rue de l'usine";67520;MARLENHEIM;"Colle lavable à l'eau uniquement";Vert;Rose;;;"NIOGRET ALEXIS";+330663608784;"HELFFRICH PASCAL";+330620042137;;;;;5667036;5667028
                    """;
        InputStream inputStreamCsvFileWithBadSemaine = new ByteArrayInputStream(csvFileWithBadSemaine.getBytes());
        var exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamCsvFileWithBadSemaine));
        String expectedMessage = "semaine shoud be like '2022-23'";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void fileToGamesWithCP() {
        String csvFileWithBadCP = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
            2023-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";a;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";a;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
                    """;
        InputStream inputStreamCsvFileWithBadCP = new ByteArrayInputStream(csvFileWithBadCP.getBytes());
        var exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamCsvFileWithBadCP));
        String expectedMessage = "csv column cp should be a integer";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void fileToGamesWithBadScore() {
        String csvFileWithBadJ = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"sc rec";"sc vis";"fdme rec";"fdme vis";"pen. rec";"pen. vis";"forf. rec";"forf. vis";"arb1 designe";"arb2 designe";"arb1 sifle";"arb2 sifle";secretaire;chronometreur;observateur;delegue;"resp salle";"tuteur table";"code renc";"Num rec";"Num vis";Etat;Forfait;Penalite;FDME;"Date Arrivee"
            2022-36;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";1;11/09/2022;16:00:00;"COLMAR CENTRE ALSACE";HOENHEIM;a;20;25;20;0;0;0;0;"MERCHDI BILEL";"MERCHDI ZAKARIA";"MERCHDI BILEL";"MERCHDI ZAKARIA";"MALL PHILIPPE";"GERMAIN PIERRE";"HOUPERT JEREMY";;"WACKENTHALER SABINE";;SAEEHAY;5668119;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEHAY_y9SFD6F15w6kgTJrCyho9g==.pdf;"11/09/2022 17:39"
            2022-36;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";1;11/09/2022;16:00:00;"COLMAR CENTRE ALSACE";HOENHEIM;a;b;25;20;0;0;0;0;"MERCHDI BILEL";"MERCHDI ZAKARIA";"MERCHDI BILEL";"MERCHDI ZAKARIA";"MALL PHILIPPE";"GERMAIN PIERRE";"HOUPERT JEREMY";;"WACKENTHALER SABINE";;SAEEHAY;5668119;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEHAY_y9SFD6F15w6kgTJrCyho9g==.pdf;"11/09/2022 17:39"
            2022-36;F50000205G;"nationale 2 feminine 2022-23";"POULE 6";1;11/09/2022;16:00:00;"COLMAR CENTRE ALSACE";HOENHEIM;25;b;25;20;0;0;0;0;"MERCHDI BILEL";"MERCHDI ZAKARIA";"MERCHDI BILEL";"MERCHDI ZAKARIA";"MALL PHILIPPE";"GERMAIN PIERRE";"HOUPERT JEREMY";;"WACKENTHALER SABINE";;SAEEHAY;5668119;5667028;JOUE;;;http://fdmerv.ff-handball.org/S/A/E/E/SAEEHAY_y9SFD6F15w6kgTJrCyho9g==.pdf;"11/09/2022 17:39"
                    """;
        InputStream inputStreamCsvFileWithBadJ = new ByteArrayInputStream(csvFileWithBadJ.getBytes());
        var exception = assertThrows(FileDataException.class, () -> fileToGames.fileToGames(inputStreamCsvFileWithBadJ));
        String expectedMessage = "csv columns score should be a integer";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void fileToGamesPoolNumTest() throws FileException, FileDataException {
        String csvFilePoolNum = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
            2023-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
            2023-42;Z56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
            2023-42;F56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
            2023-42;56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"SOULTZ KUTZENHAUSEN SM2";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
                    """;
        when(coachRepository.findCoachByKeys(any(String.class))).thenReturn(Optional.empty());
        when(halleRepository.findHallByKeys(any(String.class), any(String.class), any(Integer.class), any(String.class))).thenReturn(Optional.empty());
        when(teamRepository.findTeamByKeys(any(Club.class), any(Gender.class), any(Category.class), any(Integer.class))).thenReturn(Optional.empty());
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            return game;
        });
        InputStream inputStreamCsvFilePoolNum = new ByteArrayInputStream(csvFilePoolNum.getBytes());
        List<Game> games = fileToGames.fileToGames(inputStreamCsvFilePoolNum);
        assertEquals(games.get(0).getHomeTeam().getGender(),games.get(0).getVisitingTeam().getGender());
        assertEquals(Gender.MALE,games.get(0).getHomeTeam().getGender());
        assertEquals(games.get(1).getHomeTeam().getGender(),games.get(1).getVisitingTeam().getGender());
        assertEquals(Gender.MIXED,games.get(1).getHomeTeam().getGender());
        assertEquals(games.get(2).getHomeTeam().getGender(),games.get(2).getVisitingTeam().getGender());
        assertEquals(Gender.FEMALE,games.get(2).getHomeTeam().getGender());
        assertEquals(games.get(3).getHomeTeam().getGender(),games.get(3).getVisitingTeam().getGender());
        assertEquals(Gender.UNKNOWN,games.get(3).getHomeTeam().getGender());
    }

    @Test
    void fileToGamesClubTest() throws FileException, FileDataException {
        Map<String,String> dataSetTest;
        String csvFileClub = """
            semaine;"num poule";competition;poule;J;le;horaire;"club rec";"club vis";"club hote";"arb1 designe";"arb2 designe";observateur;delegue;"code renc";"nom salle";"adresse salle";CP;Ville;colle;"Coul. Rec";"Coul. Gard. Rec";"Coul. Vis";"Coul. Gard. Vis";"Ent. Rec";"Tel Ent. Rec";"Corresp. Rec";"Tel Corresp. Rec";"Ent. Vis";"Tel Ent. Vis";"Corresp. Vis";"Tel Corresp. Vis";"Num rec";"Num vis"
            2023-42;M56671000G;"67-21 coupe d'encouragement credit mutuel masc";"1ER TOUR CE CCM MASC";1;22/10/2022;19:30:00;"%s";"HOENHEIM SM2";"SOULTZ KUTZENHAUSEN";;;" ";" ";SAEJPJW;"GYMNASE DU SIVU";" rue du gymnase";67250;"SOULTZ SOUS FORETS";"Toutes colles interdites";Vert;;;;"ROYER JEAN-LUC";+330611324377;"ROYER JEAN-LUC";+330611324377;;;;;5667095;5667028
                    """;
        when(coachRepository.findCoachByKeys(any(String.class))).thenReturn(Optional.empty());
        when(halleRepository.findHallByKeys(any(String.class), any(String.class), any(Integer.class), any(String.class))).thenReturn(Optional.empty());
        when(teamRepository.findTeamByKeys(any(Club.class), any(Gender.class), any(Category.class), any(Integer.class))).thenReturn(Optional.empty());
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            return game;
        });
        InputStream inputStreamCsvFileClub = new ByteArrayInputStream(csvFileClub.getBytes());

        List<Game> games = fileToGames.fileToGames(inputStreamCsvFileClub);
        assertEquals(games.get(0).getHomeTeam().getGender(),games.get(0).getVisitingTeam().getGender());
        assertEquals(Gender.MALE,games.get(0).getHomeTeam().getGender());
    }

    @Test
    void fileToGameWithHeaderPlayedAndTheSecondGame() throws FileException, FileDataException, MalformedURLException {
        InputStream inputStreamCsvFile = new ByteArrayInputStream(csvFileWithUnplayedGame.getBytes());

        String code = "SAEHOBZ";
        String competitionName = "coupe de france regionale masculine 2022-23";
        String poolCode = "M50001301R";
        String poolName = "1° TOUR";
        int dayNumber = 1;
        String halleName = "SALLE OMNISPORTS DE MARLENHEIM";
        String street = "2 b, rue de l'usine";
        int postalCode = 67520;
        String city = "MARLENHEIM";
        GlueAuthorization glueAuthorization = GlueAuthorization.AUTHORIZED;
        String designatedReferee1Name = "NOEL ARTHUR";
        String homeTeamCategoryName = "senior";
        Gender homeTeamGender = Gender.MALE;
        int homeTeamNumber = 1;
        String homeTeamClubCode = "5667036";
        String homeTeamClubName = "MARLENHEIM";
        TeamColor homeTeamShirtColor1 = TeamColor.GREEN;
        TeamColor homeTeamGoalkeeperColor1 = TeamColor.PINK;
        String homeTeamCoachName = "NIOGRET ALEXIS";
        String homeTeamCoachPhoneNumber = "+330663608784";
        String visitingTeamCategoryName = "senior";
        Gender visitingTeamGender = Gender.MALE;
        int visitingTeamNumber = 1;
        String visitingTeamClubCode = "5667028";
        String visitingTeamClubName = "HOENHEIM";
        TeamColor visitingTeamShirtColor1 = TeamColor.UNKNOWN;
        TeamColor visitingTeamGoalkeeperColor1 = TeamColor.UNKNOWN;
        URL fdmeUrl = new URL("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/");
        LocalDate date = LocalDate.of(2022, 10, 22);
        LocalTime time = LocalTime.of(19, 0);
        when(coachRepository.findCoachByKeys(any(String.class))).thenReturn(Optional.empty());
        when(halleRepository.findHallByKeys(any(String.class), any(String.class), any(Integer.class), any(String.class))).thenReturn(Optional.empty());
        when(refereeRepository.findRefereeByKeys(any(String.class))).thenReturn(Optional.empty());
        when(teamRepository.findTeamByKeys(any(Club.class), any(Gender.class), any(Category.class), any(Integer.class))).thenReturn(Optional.empty());
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game game = invocation.getArgument(0);
            return game;
        });

        List<Game> games = fileToGames.fileToGames(inputStreamCsvFile);
        Game game = games.get(1);
        assertEquals(code, game.getCode());
        assertEquals(competitionName, game.getCompetition().name());
        assertEquals(poolCode, game.getCompetition().pool().code());
        assertEquals(poolName, game.getCompetition().pool().name());
        assertEquals(dayNumber, game.getDay().number());
        assertEquals(halleName, game.getHalle().name());
        assertEquals(street, game.getHalle().address().street());
        assertEquals(postalCode, game.getHalle().address().postalCode());
        assertEquals(city, game.getHalle().address().city());
        assertEquals(glueAuthorization, game.getHalle().glueAuthorization());
        assertEquals(designatedReferee1Name, game.getReferees().designatedReferee1().name());
        assertEquals(Referee.UNKNOWN, game.getReferees().designatedReferee2());
        assertEquals(homeTeamCategoryName, game.getHomeTeam().getCategory().name());
        assertEquals(homeTeamGender, game.getHomeTeam().getGender());
        assertEquals(homeTeamNumber, game.getHomeTeam().getNumber());
        assertEquals(homeTeamClubCode, game.getHomeTeam().getClub().code());
        assertEquals(homeTeamClubName, game.getHomeTeam().getClub().name());
        assertEquals(homeTeamShirtColor1, game.getHomeTeam().getTeamsColor().shirtColor1());
        assertEquals(homeTeamGoalkeeperColor1, game.getHomeTeam().getTeamsColor().goalkeeperColor1());
        assertEquals(homeTeamCoachName, game.getHomeTeam().getCoach().name());
        assertEquals(homeTeamCoachPhoneNumber, game.getHomeTeam().getCoach().phoneNumber().phoneNumber());
        assertEquals(visitingTeamCategoryName, game.getVisitingTeam().getCategory().name());
        assertEquals(visitingTeamGender, game.getVisitingTeam().getGender());
        assertEquals(visitingTeamNumber, game.getVisitingTeam().getNumber());
        assertEquals(visitingTeamClubCode, game.getVisitingTeam().getClub().code());
        assertEquals(visitingTeamClubName, game.getVisitingTeam().getClub().name());
        assertEquals(visitingTeamShirtColor1, game.getVisitingTeam().getTeamsColor().shirtColor1());

        assertEquals(visitingTeamGoalkeeperColor1, game.getVisitingTeam().getTeamsColor().goalkeeperColor1());
        assertEquals(Coach.UNKNOWN, game.getVisitingTeam().getCoach());
        assertEquals(Score.DEFAULT, game.getScore());
        assertEquals(fdmeUrl, game.getFdme().url());
        assertEquals(date, game.getDate());
        assertEquals(time, game.getTime());
    }
}