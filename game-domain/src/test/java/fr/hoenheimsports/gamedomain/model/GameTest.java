package fr.hoenheimsports.gamedomain.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Game(null, Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", null, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023,null, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, null, Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, null, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), null, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, null, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, null, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, null, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertThrows(NullPointerException.class, () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, null, LocalDate.now(), LocalTime.now()));
        assertDoesNotThrow( () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, null, LocalTime.now()));
        assertDoesNotThrow( () -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), null));
    }

    @Test
    public void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
        assertDoesNotThrow(() -> new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, null, FDME.UNKNOWN, LocalDate.now(), LocalTime.now()));
    }

    @Test
    public void testScoreNotNull() {
        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, null, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());
        assertNotNull(game.getScore());
    }

    @Test
    public void testSetHalle() {
        Hall halle1 = Hall.UNKNOWN;
        Hall halle2 = Hall.UNKNOWN;

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), halle1, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setHalle(halle2);

        assertEquals(halle2, game.getHalle());
    }

    @Test
    public void testSetReferees() {
        Referees referees1 = Referees.UNKNOWN;
        Referees referees2 = Referees.UNKNOWN;

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, referees1, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setReferees(referees2);

        assertEquals(referees2, game.getReferees());
    }

    @Test
    public void testSetScore() {
        Score score1 = Score.DEFAULT;
        Score score2 = Score.DEFAULT;

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, score1, FDME.UNKNOWN, LocalDate.now(), LocalTime.now());

        game.setScore(score2);

        assertEquals(score2, game.getScore());
    }

    @Test
    public void testSetFdme() {
        FDME fdme1 = FDME.UNKNOWN;
        FDME fdme2 = FDME.UNKNOWN;

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, fdme1, LocalDate.now(), LocalTime.now());

        game.setFdme(fdme2);

        assertEquals(fdme2, game.getFdme());
    }

    @Test
    public void testSetDate() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, date1, LocalTime.now());

        game.setDate(date2);

        assertEquals(date2, game.getDate());
    }
    @Test
    public void testSetTime() {
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now();

        Game game = new Game("code", Pool.UNKNOWN, Season.SEASON_2022_2023, Day.SINGLE_DAY_GAME, new Week(LocalDate.now()), Hall.UNKNOWN, Referees.UNKNOWN, Team.UNKNOWN, Team.UNKNOWN, Score.DEFAULT, FDME.UNKNOWN, null, time1);

        game.setTime(time2);

        assertEquals(time2, game.getTime());
    }

    @Test
    public void testEquals() {
        String code = "code";
        Pool pool = Pool.UNKNOWN;
        Day day = Day.SINGLE_DAY_GAME;
        Hall halle = Hall.UNKNOWN;
        Referees referees = Referees.UNKNOWN;
        Team homeTeam = Team.UNKNOWN;
        Team visitingTeam = Team.UNKNOWN;
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate dateTime = LocalDate.now();

        Game game1 = new Game(code, pool, Season.SEASON_2022_2023, day, new Week(LocalDate.now()), halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());
        Game game2 = new Game(code, pool, Season.SEASON_2022_2023, day, new Week(LocalDate.now()), halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());

        assertEquals(game1, game2);
    }

    @Test
    public void testHashCode() {
        String code = "code";
        Pool pool = Pool.UNKNOWN;
        Day day = Day.SINGLE_DAY_GAME;
        Hall halle = Hall.UNKNOWN;
        Referees referees = Referees.UNKNOWN;
        Team homeTeam = Team.UNKNOWN;
        Team visitingTeam = Team.UNKNOWN;
        Score score = Score.DEFAULT;
        FDME fdme = FDME.UNKNOWN;
        LocalDate dateTime = LocalDate.now();

        Game game1 = new Game(code, pool, Season.SEASON_2022_2023, day, new Week(LocalDate.now()), halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());
        Game game2 = new Game(code, pool, Season.SEASON_2022_2023, day, new Week(LocalDate.now()), halle, referees, homeTeam, visitingTeam, score, fdme, dateTime, LocalTime.now());

        assertEquals(game1.hashCode(), game2.hashCode());
    }


}