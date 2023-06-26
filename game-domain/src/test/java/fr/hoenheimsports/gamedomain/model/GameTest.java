package fr.hoenheimsports.gamedomain.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Game(null, Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", null, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, null, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, null, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, null, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, null, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, null, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, null, LocalDate.now(), LocalTime.now()));
        assertDoesNotThrow( () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, null, LocalTime.now()));
        assertDoesNotThrow( () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), null));
    }

    @Test
    public void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertDoesNotThrow(() -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, null, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
    }

    @Test
    public void testScoreNotNull() {
        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, null, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());
        assertNotNull(game.getScore());
    }

    @Test
    public void testSetHalle() {
        Halle halle1 = Halle.UNKNOWN;
        Halle halle2 = Halle.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, halle1, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setHalle(halle2);

        assertEquals(halle2, game.getHalle());
    }

    @Test
    public void testSetReferees() {
        Referees referees1 = Referees.UNKNOWN;
        Referees referees2 = Referees.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, referees1, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setReferees(referees2);

        assertEquals(referees2, game.getReferees());
    }

    @Test
    public void testSetScore() {
        Score score1 = Score.DEFAULT;
        Score score2 = Score.DEFAULT;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, score1, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setScore(score2);

        assertEquals(score2, game.getScore());
    }

    @Test
    public void testSetFdme() {
        FDME fdme1 = FDME.UNKNOWN;
        FDME fdme2 = FDME.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, fdme1, LocalDate.now(), LocalTime.now());

        game.setFdme(fdme2);

        assertEquals(fdme2, game.getFdme());
    }

    @Test
    public void testSetDate() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, date1, LocalTime.now());

        game.setDate(date2);

        assertEquals(date2, game.getDate());
    }
    @Test
    public void testSetTime() {
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now();

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, null, time1);

        game.setTime(time2);

        assertEquals(time2, game.getTime());
    }

    @Test
    public void testEquals() {
        String code = "code";
        Competition competition = Competition.UNKNOWN;
        Day day = Day.SINGLE_DAY_GAME;
        Halle halle = Halle.UNKNOWN;
        Referees referees = Referees.UNKNOWN;
        Team homeTeam = Team.UNKNOWN;
        Team visitingTeam = Team.UNKNOWN;
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate dateTime = LocalDate.now();

        Game game1 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());
        Game game2 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());

        assertEquals(game1, game2);
    }

    @Test
    public void testHashCode() {
        String code = "code";
        Competition competition = Competition.UNKNOWN;
        Day day = Day.SINGLE_DAY_GAME;
        Halle halle = Halle.UNKNOWN;
        Referees referees = Referees.UNKNOWN;
        Team homeTeam = Team.UNKNOWN;
        Team visitingTeam = Team.UNKNOWN;
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate dateTime = LocalDate.now();

        Game game1 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());
        Game game2 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());

        assertEquals(game1.hashCode(), game2.hashCode());
    }

    @Test
    public void testToString() {
        String code = "code";
        Competition competition = Competition.UNKNOWN;
        Day day = Day.SINGLE_DAY_GAME;
        Halle halle = Halle.UNKNOWN;
        Referees referees = Referees.UNKNOWN;
        Team homeTeam = Team.UNKNOWN;
        Team visitingTeam = Team.UNKNOWN;
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Game game = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);

        String expected = "Game[code=code, competition=Competition[name=unknown, pool=Pool[code=unknown, name=unknown]], day=Day[number=0], halle=Halle[id=00000000-0000-0000-0000-000000000000, name=unknown, address=Address[street=unknown, postalCode=0, city=unknown], glueAuthorization=UNKNOWN], referees=Referees[designatedReferee1=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown], designatedReferee2=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown], officiatingReferee1=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown], officiatingReferee2=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown]], homeTeam=Team[id=00000000-0000-0000-0000-000000000000, category=Category[name=unknown], gender=UNKNOWN, code=1, club=Club[code=00000000-0000-0000-0000-000000000000, name=unknown], teamsColor=TeamsColor[shirtColor1=null, shirtColor2=null, goalkeeperColor1=null, goalkeeperColor2=null], coach=Coach[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]]], visitingTeam=Team[id=00000000-0000-0000-0000-000000000000, category=Category[name=unknown], gender=UNKNOWN, code=1, club=Club[code=00000000-0000-0000-0000-000000000000, name=unknown], teamsColor=TeamsColor[shirtColor1=null, shirtColor2=null, goalkeeperColor1=null, goalkeeperColor2=null], coach=Coach[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]]], score=Score[homeScore=0, visitingScore=0], fdme=FDME[url=https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/], date=" + date + ", time=" + time + "]";
        assertEquals(expected, game.toString());
    }
}