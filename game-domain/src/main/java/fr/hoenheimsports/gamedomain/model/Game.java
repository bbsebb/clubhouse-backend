package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public final class Game {

    private final String code;
    private final Competition competition;
    private final Day day;
    private Halle halle;
    private Referees referees;
    private final Team homeTeam;
    private final Team visitingTeam;
    private Score score;
    private FDME fdme;
    private LocalDate date;
    private LocalTime time;

    public Game(String code, Competition competition, Day day, Halle halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDate date,LocalTime time) {
        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(competition, "competition should not be null");
        Objects.requireNonNull(day, "day should not be null");
        Objects.requireNonNull(halle, "halle should not be null");
        Objects.requireNonNull(referees, "referees should not be null");
        Objects.requireNonNull(homeTeam, "homeTeam should not be null");
        Objects.requireNonNull(visitingTeam, "visitingTeam should not be null");
        Objects.requireNonNull(fdme, "fdme should not be null");
        this.code = code;
        this.competition = competition;
        this.day = day;
        this.halle = halle;
        this.referees = referees;
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.score = Objects.requireNonNullElse(score, Score.DEFAULT);;
        this.fdme = fdme;
        this.date = date;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Day getDay() {
        return day;
    }

    public Halle getHalle() {
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

    public void setHalle(Halle halle) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Game) obj;
        return Objects.equals(this.code, that.code) &&
                Objects.equals(this.competition, that.competition) &&
                Objects.equals(this.day, that.day) &&
                Objects.equals(this.halle, that.halle) &&
                Objects.equals(this.referees, that.referees) &&
                Objects.equals(this.homeTeam, that.homeTeam) &&
                Objects.equals(this.visitingTeam, that.visitingTeam) &&
                Objects.equals(this.score, that.score) &&
                Objects.equals(this.fdme, that.fdme) &&
                Objects.equals(this.date, that.date) &&
                Objects.equals(this.time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);
    }

    @Override
    public String toString() {
        return "Game[" +
                "code=" + code + ", " +
                "competition=" + competition + ", " +
                "day=" + day + ", " +
                "halle=" + halle + ", " +
                "referees=" + referees + ", " +
                "homeTeam=" + homeTeam + ", " +
                "visitingTeam=" + visitingTeam + ", " +
                "score=" + score + ", " +
                "fdme=" + fdme + ", " +
                "date=" + date + ", " +
                "time=" + time + ']';
    }

}
