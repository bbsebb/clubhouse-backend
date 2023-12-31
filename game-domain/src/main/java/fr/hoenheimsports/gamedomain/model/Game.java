package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public final class Game {

    private final String code;
    private final Pool pool;
    private final Season season;

    private final Day day;
    private final Week week;
    private Hall halle;
    private Referees referees;
    private final Team homeTeam;
    private final Team visitingTeam;
    private Score score;
    private FDME fdme;
    private LocalDate date;
    private LocalTime time;

    public Game(String code, Pool pool, Season season, Day day, Week week, Hall halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date, LocalTime time) {

        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(pool, "pool should not be null");
        Objects.requireNonNull(day, "day should not be null");
        Objects.requireNonNull(week, "week should not be null");
        Objects.requireNonNull(season, "season should not be null");
        Objects.requireNonNull(halle, "halle should not be null");
        Objects.requireNonNull(referees, "referees should not be null");
        Objects.requireNonNull(homeTeam, "homeTeam should not be null");
        Objects.requireNonNull(visitingTeam, "visitingTeam should not be null");
        Objects.requireNonNull(fdme, "fdme should not be null");
        this.code = code;
        this.pool = pool;
        this.day = day;
        this.week = week;
        this.season = season;
        this.halle = halle;
        this.referees = referees;
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.score = Objects.requireNonNullElse(score, Score.DEFAULT);
        this.fdme = fdme;
        this.date = date;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public Pool getPool() {
        return pool;
    }

    public Season getSeason() {
        return season;
    }

    public Week getWeek() {
        return week;
    }

    public Day getDay() {
        return day;
    }

    public Hall getHalle() {
        return halle;
    }

    public Referees getReferees() {
        return referees;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public Score getScore() {
        return score;
    }

    public FDME getFdme() {
        return fdme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setHalle(Hall halle) {
        this.halle = halle;
    }

    public void setReferees(Referees referees) {
        this.referees = referees;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setFdme(FDME fdme) {
        this.fdme = fdme;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public boolean isPlayed() {
        return this.score.homeScore() + this.score.visitingScore() > 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return Objects.equals(code, game.code) && Objects.equals(pool, game.pool) && Objects.equals(season, game.season) && Objects.equals(day, game.day) && Objects.equals(week, game.week) && Objects.equals(halle, game.halle) && Objects.equals(referees, game.referees) && Objects.equals(homeTeam, game.homeTeam) && Objects.equals(visitingTeam, game.visitingTeam) && Objects.equals(score, game.score) && Objects.equals(fdme, game.fdme) && Objects.equals(date, game.date) && Objects.equals(time, game.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, pool, season, day, week, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);
    }

    @Override
    public String toString() {
        return "Game{" +
                "code='" + code + '\'' +
                ", pool=" + pool +
                ", season=" + season +
                ", day=" + day +
                ", week=" + week +
                ", halle=" + halle +
                ", referees=" + referees +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", score=" + score +
                ", fdme=" + fdme +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
