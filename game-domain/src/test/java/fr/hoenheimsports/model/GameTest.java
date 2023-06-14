package fr.hoenheimsports.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Game(null, Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", null, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, null, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, null, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, null, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, null, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, null, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));

        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, null, LocalDateTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, null));
    }

    @Test
    public void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now()));
        assertDoesNotThrow(() -> new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, null, FDME.UNKNOWN, LocalDateTime.now()));
    }

    @Test
    public void testSetHalle() {
        Halle halle1 = Halle.UNKNOWN;
        Halle halle2 = Halle.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, halle1, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now());

        game.setHalle(halle2);

        assertEquals(halle2, game.halle());
    }

    @Test
    public void testSetReferees() {
        Referees referees1 = Referees.UNKNOWN;
        Referees referees2 = Referees.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, referees1, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDateTime.now());

        game.setReferees(referees2);

        assertEquals(referees2, game.referees());
    }

    @Test
    public void testSetScore() {
        Score score1 = Score.DEFAULT;
        Score score2 = Score.DEFAULT;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, score1, FDME.UNKNOWN, LocalDateTime.now());

        game.setScore(score2);

        assertEquals(score2, game.score());
    }

    @Test
    public void testSetFdme() {
        FDME fdme1 = FDME.UNKNOWN;
        FDME fdme2 = FDME.UNKNOWN;

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, fdme1, LocalDateTime.now());

        game.setFdme(fdme2);

        assertEquals(fdme2, game.fdme());
    }

    @Test
    public void testSetDateTime() {
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.now();

        Game game = new Game("code", Competition.UNKNOWN, Day.SINGLE_DAY_GAME, Halle.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, dateTime1);

        game.setDateTime(dateTime2);

        assertEquals(dateTime2, game.dateTime());
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
        LocalDateTime dateTime = LocalDateTime.now();

        Game game1 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);
        Game game2 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);

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
        LocalDateTime dateTime = LocalDateTime.now();

        Game game1 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);
        Game game2 = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);

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
        LocalDateTime dateTime = LocalDateTime.now();

        Game game = new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);

        String expected = "Game[code=code, competition=Competition[name=unknown, pools=[Pool[number=unknown, name=unknown]]], day=Day[number=0], halle=Halle[id=00000000-0000-0000-0000-000000000000, name=unknown, address=Address[street=unknown, postalCode=0, city=unknown], glueAuthorization=UNKNOWN], referees=Referees[designatedReferee1=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]], designatedReferee2=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]], officiatingReferee1=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]], officiatingReferee2=Referee[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]]], homeTeam=Team[id=00000000-0000-0000-0000-000000000000, category=Category[name=unknown], gender=UNKNOWN, number=1, club=Club[code=00000000-0000-0000-0000-000000000000, name=unknown], teamsColor=TeamsColor[shirtColor1=null, shirtColor2=null, goalkeeperColor1=null, goalkeeperColor2=null], coach=Coach[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]]], visitingTeam=Team[id=00000000-0000-0000-0000-000000000000, category=Category[name=unknown], gender=UNKNOWN, number=1, club=Club[code=00000000-0000-0000-0000-000000000000, name=unknown], teamsColor=TeamsColor[shirtColor1=null, shirtColor2=null, goalkeeperColor1=null, goalkeeperColor2=null], coach=Coach[id=00000000-0000-0000-0000-000000000000, name=unknown, phoneNumber=PhoneNumber[phoneNumber=00]]], score=Score[homeScore=0, visitingScore=0], fdme=FDME[url=https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/], dateTime=" + dateTime + "]";
        assertEquals(expected, game.toString());
    }
}