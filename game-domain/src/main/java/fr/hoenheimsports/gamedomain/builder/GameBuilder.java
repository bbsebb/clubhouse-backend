package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.function.Consumer;

public class GameBuilder {
    public static GameBuilder builder() {
        return new GameBuilder();
    }
    private String code;
    private Competition competition;
    private Season season;
    private Week week;
    private Day day;
    private Halle halle;
    private Referees referees;
    private Team homeTeam;
    private Team visitingTeam;
    private Score score;
    private FDME fdme;
    private LocalDate date;
    private LocalTime time;

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

    public GameBuilder withSeason(Season season) {
        this.season = season;
        return this;
    }

    public GameBuilder withSeason(Consumer<SeasonBuilder> seasonBuilderFunction) {
        SeasonBuilder seasonBuilder = new SeasonBuilder();
        seasonBuilderFunction.accept(seasonBuilder);
        this.season = seasonBuilder.build();
        return this;
    }

    public GameBuilder withWeek(Week week) {
        this.week = week;
        return this;
    }

    public GameBuilder withWeek(Consumer<WeekBuilder> weekBuilderFunction) {
        WeekBuilder weekBuilder = new WeekBuilder();
        weekBuilderFunction.accept(weekBuilder);
        this.week = weekBuilder.build();
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

    public GameBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }
    public GameBuilder withTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public Game build() {
        if(code == null) {
            code = UUID.randomUUID().toString();
        }
        return new Game(code, competition, season, day, week, halle, referees, homeTeam, visitingTeam, score, fdme, date,time);
    }
}

/*
    Exemple minimum avec GameBuilder :

            Game game1 = GameBuilder.builder()
                .withCode("test1")
                .withCompetition(Competition.UNKNOWN)
                .withDay(Day.SINGLE_DAY_GAME)
                .withFDME(FDME.UNKNOWN)
                .withHalle(Halle.UNKNOWN)
                .withHomeTeam(Team.UNKNOWN)
                .withVisitingTeam(Team.UNKNOWN)
                .withReferees(Referees.UNKNOWN)
                .withScore(Score.DEFAULT)
                .build();


    Exemple avec complet GameBuilder :

    GameBuilder.builder()
                .withCode(gameDTO.code())
                .withDate(gameDTO.date())
                .withTime(gameDTO.time())
                .withDay(Day.SINGLE_DAY_GAME)
                .withCompetition(Competition.UNKNOWN)
                .withHalle(halleBuilder -> halleBuilder
                        .addIdGeneratorFromRepository(this.halleRepository)
                        .withName(gameDTO.halle().name())
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet(gameDTO.halle().address().street())
                                .withPostalCode(gameDTO.halle().address().postalCode())
                                .withStreet(gameDTO.halle().address().city())))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .addIdGeneratorFromRepository(this.teamRepository)
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(gameDTO.homeTeam().club().code())
                                .withName(gameDTO.homeTeam().club().name()))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName(gameDTO.homeTeam().category().name()))
                        .withGender(gameDTO.homeTeam().gender())
                        .withNumber(gameDTO.homeTeam().number())
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withShirtColor1(gameDTO.homeTeam().teamsColor().shirtColor1())
                                .withShirtColor2(gameDTO.homeTeam().teamsColor().shirtColor2())
                                .withGoalkeeperColor1(gameDTO.homeTeam().teamsColor().goalkeeperColor1())
                                .withGoalkeeperColor2(gameDTO.homeTeam().teamsColor().goalkeeperColor2()))
                        .withCoach(coachBuilder -> coachBuilder
                                .addIdGeneratorFromRepository(this.coachRepository)
                                .withName(gameDTO.homeTeam().coach().name())
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber(gameDTO.homeTeam().coach().phoneNumber().phoneNumber()))))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .addIdGeneratorFromRepository(this.teamRepository)
                        .withClub(clubBuilder -> clubBuilder
                                .withCode(gameDTO.visitingTeam().club().code())
                                .withName(gameDTO.visitingTeam().club().name()))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName(gameDTO.visitingTeam().category().name()))
                        .withGender(gameDTO.visitingTeam().gender())
                        .withNumber(gameDTO.visitingTeam().number())
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withShirtColor1(gameDTO.visitingTeam().teamsColor().shirtColor1())
                                .withShirtColor2(gameDTO.visitingTeam().teamsColor().shirtColor2())
                                .withGoalkeeperColor1(gameDTO.visitingTeam().teamsColor().goalkeeperColor1())
                                .withGoalkeeperColor2(gameDTO.visitingTeam().teamsColor().goalkeeperColor2()))
                        .withCoach(coachBuilder -> coachBuilder
                                .addIdGeneratorFromRepository(this.coachRepository)
                                .withName(gameDTO.visitingTeam().coach().name())
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber(gameDTO.visitingTeam().coach().phoneNumber().phoneNumber()))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(gameDTO.score().homeScore())
                        .withVisitingScore(gameDTO.score().visitingScore()))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().designatedReferee1().name()))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().designatedReferee2().name()))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().officiatingReferee1().name()))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .addIdGeneratorFromRepository(this.refereeRepository)
                                .withName(gameDTO.referees().officiatingReferee2().name())))
                .withFDME(fdmeBuilder -> fdmeBuilder
                        .withUrl(gameDTO.fdme().url()))
                .build();
            */
