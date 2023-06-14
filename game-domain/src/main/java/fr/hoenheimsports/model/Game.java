package fr.hoenheimsports.model;

import java.time.LocalDateTime;
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
    private LocalDateTime dateTime;

    public Game(String code, Competition competition, Day day, Halle halle, Referees referees, Team homeTeam, Team visitingTeam, Score score, FDME fdme, LocalDateTime dateTime) {
        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(competition, "competition should not be null");
        Objects.requireNonNull(day, "day should not be null");
        Objects.requireNonNull(halle, "halle should not be null");
        Objects.requireNonNull(referees, "referees should not be null");
        Objects.requireNonNull(homeTeam, "homeTeam should not be null");
        Objects.requireNonNull(visitingTeam, "visitingTeam should not be null");
        Objects.requireNonNullElse(score, Score.DEFAULT);
        Objects.requireNonNull(fdme, "fdme should not be null");
        Objects.requireNonNull(dateTime, "dateTime should not be null");
        this.code = code;
        this.competition = competition;
        this.day = day;
        this.halle = halle;
        this.referees = referees;
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.score = score;
        this.fdme = fdme;
        this.dateTime = dateTime;
    }

    public String code() {
        return code;
    }

    public Competition competition() {
        return competition;
    }

    public Day day() {
        return day;
    }

    public Halle halle() {
        return halle;
    }

    public Referees referees() {
        return referees;
    }

    public Team homeTeam() {
        return homeTeam;
    }

    public Team visitingTeam() {
        return visitingTeam;
    }

    public Score score() {
        return score;
    }

    public FDME fdme() {
        return fdme;
    }

    public LocalDateTime dateTime() {
        return dateTime;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
                Objects.equals(this.dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);
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
                "dateTime=" + dateTime + ']';
    }

}
