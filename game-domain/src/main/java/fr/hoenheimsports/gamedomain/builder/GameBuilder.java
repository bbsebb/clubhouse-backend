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
        return new Game(code, competition, day, halle, referees, homeTeam, visitingTeam, score, fdme, date,time);
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

     Game game1 = GameBuilder.builder()
                .withCode("code game 1")
                .withDate(LocalDate.now())
                .withTime(LocalTime.now())
                .withDay(dayBuilder -> dayBuilder.withNumber(1).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 1")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool1")
                                .withCode("code Pool 1")))
                .withHalle(halleBuilder -> halleBuilder
                        .withId(UUID.randomUUID())
                        .withName("Halle 1")
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet("rue 1")
                                .withPostalCode(67000)
                                .withCity("ville 1"))
                        .withGlueAuthorization(GlueAuthorization.AUTHORIZED))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 1")
                                .withName("Club 1"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 1"))
                        .withGender(Gender.MALE)
                        .withNumber(1)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.BEIGE)
                                .withGoalkeeperColor2(TeamColor.BLACK)
                                .withShirtColor1(TeamColor.BLUE)
                                .withShirtColor2(TeamColor.GARNET))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("Name 1")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("0000000000"))))
                .withVisitingTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 2")
                                .withName("Club 2"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 2"))
                        .withGender(Gender.FEMALE)
                        .withNumber(1)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.BURGUNDY)
                                .withGoalkeeperColor2(TeamColor.NAVY_BLUE)
                                .withShirtColor1(TeamColor.ORANGE)
                                .withShirtColor2(TeamColor.RED))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach 2")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("0000000000"))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(5)
                        .withVisitingScore(10))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 1"))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 2"))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 1"))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 2")))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://hoenheimsports.fr"))
                .build();
            */
