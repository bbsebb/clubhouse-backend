package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.*;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class GameBuilder {
    private String code;
    private Competition competition;
    private Day day;
    private Halle halle;
    private Referees referees;
    private Team homeTeam;
    private Team visitingTeam;
    private Score score;
    private FDME fdme;
    private LocalDateTime dateTime;

    public GameBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public GameBuilder withCompetition(Consumer<CompetitionBuilder> competitionBuilderFunction) {
        CompetitionBuilder competitionBuilder = new CompetitionBuilder();
        competitionBuilderFunction.accept(competitionBuilder);
        this.competition = competitionBuilder.build();
        return this;
    }

    public GameBuilder withCompetition(Competition competition ) {
        this.competition = competition;
        return this;
    }

    public GameBuilder withDay(Consumer<DayBuilder> dayBuilderFunction) {
        DayBuilder dayBuilder = new DayBuilder();
        dayBuilderFunction.accept(dayBuilder);
        this.day = dayBuilder.build();
        return this;
    }

    public GameBuilder withDay(Day day) {
        this.day = day;
        return this;
    }

    public GameBuilder withHalle(Consumer<HalleBuilder> halleBuilderFunction) {
        HalleBuilder halleBuilder = new HalleBuilder();
        halleBuilderFunction.accept(halleBuilder);
        this.halle = halleBuilder.build();
        return this;
    }

    public GameBuilder withHalle(Halle halle) {
        this.halle = halle;
        return this;
    }

    public GameBuilder withReferees(Consumer<RefereesBuilder> refereesBuilderFunction) {
        RefereesBuilder refereesBuilder = new RefereesBuilder();
        refereesBuilderFunction.accept(refereesBuilder);
        this.referees = refereesBuilder.build();
        return this;
    }
    public GameBuilder withReferees(Referees referees) {
        this.referees = referees;
        return this;
    }

    public GameBuilder withHomeTeam(Consumer<TeamBuilder> homeTeamBuilderFunction) {
        TeamBuilder homeTeamBuilder = new TeamBuilder();
        homeTeamBuilderFunction.accept(homeTeamBuilder);
        this.homeTeam = homeTeamBuilder.build();
        return this;
    }

    public GameBuilder withHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        return this;
    }

    public GameBuilder withVisitingTeam(Consumer<TeamBuilder> visitingTeamBuilderFunction) {
        TeamBuilder visitingTeamBuilder = new TeamBuilder();
        visitingTeamBuilderFunction.accept(visitingTeamBuilder);
        this.visitingTeam = visitingTeamBuilder.build();
        return this;
    }

    public GameBuilder withVisitingTeam(Team visitingTeam) {
        this.visitingTeam = visitingTeam;
        return this;
    }

    public GameBuilder withScore(Consumer<ScoreBuilder> scoreBuilderFunction) {
        ScoreBuilder scoreBuilder = new ScoreBuilder();
        scoreBuilderFunction.accept(scoreBuilder);
        this.score = scoreBuilder.build();
        return this;
    }

    public GameBuilder withScore(Score score) {
        this.score = score;
        return this;
    }

    public GameBuilder withFDME(Consumer<FDMEBuilder> fdmeBuilderFunction) {
        FDMEBuilder fdmeBuilder = new FDMEBuilder();
        fdmeBuilderFunction.accept(fdmeBuilder);
        this.fdme = fdmeBuilder.build();
        return this;
    }

    public GameBuilder withFDME(FDME fdme) {
        this.fdme = fdme;
        return this;
    }

    public GameBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Game build() {
        return new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, dateTime);
    }
}
