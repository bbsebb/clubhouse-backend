package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class GameEntity {
    @Id
    private String code;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PoolEntity pool;
    @Embedded
    private DayEntity day;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SeasonEntity season;
    @Embedded
    private WeekEntity week;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private HallEntity halle;
    @Embedded
    private RefereesEntity referees;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TeamEntity homeTeam;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TeamEntity visitingTeam;
    @Embedded
    private ScoreEntity score;
    @Embedded
    private FDMEEntity fdme;

    private LocalDate date;

    private LocalTime time;

    public GameEntity() {
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PoolEntity getPool() {
        return pool;
    }

    public void setPool(PoolEntity pool) {
        this.pool = pool;
    }

    public DayEntity getDay() {
        return day;
    }

    public void setDay(DayEntity day) {
        this.day = day;
    }

    public HallEntity getHalle() {
        return halle;
    }

    public void setHalle(HallEntity halle) {
        this.halle = halle;
    }

    public RefereesEntity getReferees() {
        return referees;
    }

    public void setReferees(RefereesEntity referees) {
        this.referees = referees;
    }

    public TeamEntity getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamEntity homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamEntity getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(TeamEntity visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public ScoreEntity getScore() {
        return score;
    }

    public void setScore(ScoreEntity score) {
        this.score = score;
    }

    public FDMEEntity getFdme() {
        return fdme;
    }

    public void setFdme(FDMEEntity fdme) {
        this.fdme = fdme;
    }

    public LocalDate getDate() {
        return date;
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

    public SeasonEntity getSeason() {
        return season;
    }

    public void setSeason(SeasonEntity season) {
        this.season = season;
    }

    public WeekEntity getWeek() {
        return week;
    }

    public void setWeek(WeekEntity week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameEntity that)) return false;
        return Objects.equals(code, that.code) && Objects.equals(pool, that.pool) && Objects.equals(day, that.day) && Objects.equals(season, that.season) && Objects.equals(week, that.week) && Objects.equals(halle, that.halle) && Objects.equals(referees, that.referees) && Objects.equals(homeTeam, that.homeTeam) && Objects.equals(visitingTeam, that.visitingTeam) && Objects.equals(score, that.score) && Objects.equals(fdme, that.fdme) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, pool, day, season, week, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);
    }
}
