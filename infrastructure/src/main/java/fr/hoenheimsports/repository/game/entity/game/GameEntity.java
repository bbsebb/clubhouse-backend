package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class GameEntity {
    @Id
    private String code;
    @ManyToOne(cascade = CascadeType.ALL)
    private CompetitionEntity competition;
    @Embedded
    private DayEntity day;
    @ManyToOne(cascade = CascadeType.ALL)
    private SeasonEntity season;
    @Embedded
    private WeekEntity week;
    @ManyToOne(cascade = CascadeType.ALL)
    private HalleEntity halle;
    @Embedded
    private RefereesEntity referees;
    @ManyToOne(cascade = CascadeType.ALL)
    private TeamEntity homeTeam;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionEntity competition) {
        this.competition = competition;
    }

    public DayEntity getDay() {
        return day;
    }

    public void setDay(DayEntity day) {
        this.day = day;
    }

    public HalleEntity getHalle() {
        return halle;
    }

    public void setHalle(HalleEntity halle) {
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
        return Objects.equals(code, that.code) && Objects.equals(competition, that.competition) && Objects.equals(day, that.day) && Objects.equals(halle, that.halle) && Objects.equals(referees, that.referees) && Objects.equals(homeTeam, that.homeTeam) && Objects.equals(visitingTeam, that.visitingTeam) && Objects.equals(score, that.score) && Objects.equals(fdme, that.fdme) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, date, time);
    }
}
