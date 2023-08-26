package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.*;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class GameBuilderTest {
    @Test
    public void testBuilderMethod() {
        GameBuilder gameBuilder = GameBuilder.builder();
        assertNotNull(gameBuilder);
    }
    @Test
    void testGameBuilderWithObject() throws MalformedURLException {
        // Create necessary objects using builders
        Game gameExcepted = new Game(
                "game123",
                new Competition(
                        "Competition1",
                        new Pool(
                                "Pool1",
                                "Pool1"
                        )
                ),
                Season.SEASON_2022_2023, new Day(1),
                new Week(LocalDate.now()), new Hall(
                        UUID.randomUUID(),
                        "Halle1",
                        new Address("Street2", 67890, "City2"),
                        GlueAuthorization.UNKNOWN
                ),
                new Referees(
                        new Referee(UUID.randomUUID(), "Referee1"),
                        new Referee(UUID.randomUUID(), "Referee2"),
                        new Referee(UUID.randomUUID(), "Referee3"),
                        new Referee(UUID.randomUUID(), "Referee4")
                ),
                new Team(
                        UUID.randomUUID(),
                        new Category("-18 ans", 18, true),
                        Gender.MALE,
                        1,
                        new Club("Club1", "ClubName1", Set.of(Hall.UNKNOWN)),
                        new TeamsColor(TeamColor.BEIGE,TeamColor.BROWN,TeamColor.BLACK,TeamColor.BURGUNDY),
                        new Coach(UUID.randomUUID(), "Coach1", new PhoneNumber("1111111111"))

                ),
                new Team(
                        UUID.randomUUID(),
                        new Category("-18 ans", 18, true),
                        Gender.FEMALE,
                        5,
                        new Club("Club2", "ClubName2",Set.of(Hall.UNKNOWN)),
                        new TeamsColor(TeamColor.BLACK,TeamColor.GARNET,TeamColor.NAVY_BLUE,TeamColor.GREY),
                        new Coach(UUID.randomUUID(), "Coach2", new PhoneNumber("2222222222"))
                ),
                Score.DEFAULT,
                new FDME(new URL("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/")),
                LocalDate.now(),
                LocalTime.now()
        );

        // Build a Game object using GameBuilder
        Game game = new GameBuilder()
                .withCode(gameExcepted.getCode())
                .withCompetition(gameExcepted.getCompetition())
                .withDay(gameExcepted.getDay())
                .withWeek(gameExcepted.getWeek())
                .withSeason(gameExcepted.getSeason())
                .withHalle(gameExcepted.getHalle())
                .withReferees(gameExcepted.getReferees())
                .withHomeTeam(gameExcepted.getHomeTeam())
                .withVisitingTeam(gameExcepted.getVisitingTeam())
                .withScore(gameExcepted.getScore())
                .withFDME(gameExcepted.getFdme())
                .withDate(gameExcepted.getDate())
                .withTime(gameExcepted.getTime())
                .build();

        // Assertions to validate the game object
        assertEquals(gameExcepted.getCode(), game.getCode());
        assertEquals(gameExcepted.getCompetition(), game.getCompetition());
        assertEquals(gameExcepted.getWeek(), game.getWeek());
        assertEquals(gameExcepted.getSeason(), game.getSeason());
        assertEquals(gameExcepted.getDay(), game.getDay());
        assertEquals(gameExcepted.getHalle(), game.getHalle());
        assertEquals(gameExcepted.getReferees(), game.getReferees());
        assertEquals(gameExcepted.getHomeTeam(), game.getHomeTeam());
        assertEquals(gameExcepted.getVisitingTeam(), game.getVisitingTeam());
        assertEquals(gameExcepted.getScore(), game.getScore());
        assertEquals(gameExcepted.getFdme(), game.getFdme());
        assertEquals(gameExcepted.getDate(), game.getDate());
        assertEquals(gameExcepted.getTime(), game.getTime());
    }

    @Test
    void testGameBuilder() throws MalformedURLException {
        // Create necessary objects using builders

        int year = 2022;
        int week = 22;
        String seasonName = "Season 1";
        LocalDate startDateSeason = LocalDate.of(2022,1,1);

        LocalDate endDateSeason = LocalDate.of(2022,2,1);
        String competitionName = "Competition1";
        String poolNumber = "Pool1";
        String poolName = "Pool1";
        int dayNumber = 1;
        UUID halleId = UUID.randomUUID();
        String halleName = "Halle1";
        String addressStreet = "Street2";
        int addressPostalCode = 67890;
        String addressCity = "City2";
        GlueAuthorization glueAuthorization = GlueAuthorization.UNKNOWN;
        UUID referee1Id = UUID.randomUUID();
        String referee1Name = "Referee1";
        UUID referee2Id = UUID.randomUUID();
        String referee2Name = "Referee2";
        UUID referee3Id = UUID.randomUUID();
        String referee3Name = "Referee3";
        UUID referee4Id = UUID.randomUUID();
        String referee4Name = "Referee4";
        UUID homeTeamId = UUID.randomUUID();
        String homeCategoryName = "-18 ans";
        Gender homeGender = Gender.MALE;
        int homeNumber = 1;
        String homeClubCode = "Club1";
        String homeClubName = "ClubName1";
        TeamColor homeShirtColor1 = TeamColor.BEIGE;
        TeamColor homeShirtColor2 = TeamColor.BROWN;
        TeamColor homeGoalkeeperColor1 = TeamColor.BLACK;
        TeamColor homeGoalkeeperColor2 = TeamColor.BURGUNDY;
        UUID homeCoachId = UUID.randomUUID();
        String homeCoachName = "Coach1";
        String homeCoachPhoneNumber = "1111111111";
        UUID visitingTeamId = UUID.randomUUID();
        String visitingCategoryName = "senior";
        Gender visitingGender = Gender.FEMALE;
        int visitingNumber = 5;
        String visitingClubCode = "Club2";
        String visitingClubName = "ClubName2";
        TeamColor visitingShirtColor1 = TeamColor.BLACK;
        TeamColor visitingShirtColor2 = TeamColor.GARNET;
        TeamColor visitingGoalkeeperColor1 = TeamColor.NAVY_BLUE;
        TeamColor visitingGoalkeeperColor2 = TeamColor.GREY;
        UUID visitingCoachId = UUID.randomUUID();
        String visitingCoachName = "Coach2";
        String visitingCoachPhoneNumber = "2222222222";
        URL fdmeUrl = new URL("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Game game = new GameBuilder()
                .withCode("game123")
                .withWeek(weekBuilder -> weekBuilder
                        .withWeek(week)
                        .withYear(year))
                .withSeason(seasonBuilder -> seasonBuilder
                        .withName(seasonName)
                        .withStartDate(startDateSeason)
                        .withEndDate(endDateSeason))
                .withCompetition(competitionBuilder -> {
                    competitionBuilder.withName(competitionName);
                    competitionBuilder.withPool(poolBuilder -> {
                        poolBuilder.withCode(poolNumber);
                        poolBuilder.withName(poolName);
                    });
                })
                .withDay(dayBuilder -> dayBuilder.withNumber(dayNumber))
                .withHalle(halleBuilder -> {
                    halleBuilder.withId(halleId);
                    halleBuilder.withName(halleName);
                    halleBuilder.withAddress(addressBuilder -> {
                        addressBuilder.withStreet(addressStreet);
                        addressBuilder.withPostalCode(addressPostalCode);
                        addressBuilder.withCity(addressCity);
                    });
                    halleBuilder.withGlueAuthorization(glueAuthorization);
                })
                .withReferees(refereesBuilder -> {
                    refereesBuilder.withDesignatedReferee1(refereeBuilder -> {
                        refereeBuilder.withId(referee1Id);
                        refereeBuilder.withName(referee1Name);
                    });
                    refereesBuilder.withDesignatedReferee2(refereeBuilder -> {
                        refereeBuilder.withId(referee2Id);
                        refereeBuilder.withName(referee2Name);
                    });
                    refereesBuilder.withOfficiatingReferee1(refereeBuilder -> {
                        refereeBuilder.withId(referee3Id);
                        refereeBuilder.withName(referee3Name);
                    });
                    refereesBuilder.withOfficiatingReferee2(refereeBuilder -> {
                        refereeBuilder.withId(referee4Id);
                        refereeBuilder.withName(referee4Name);
                    });
                })
                .withHomeTeam(teamBuilder -> {
                    teamBuilder.withId(homeTeamId);
                    teamBuilder.withCategory(categoryBuilder -> {
                        categoryBuilder.withAge(18).withIsMaxAge(true);
                    });
                    teamBuilder.withGender(homeGender);
                    teamBuilder.withNumber(homeNumber);
                    teamBuilder.withClub(clubBuilder -> {
                        clubBuilder.withCode(homeClubCode);
                        clubBuilder.withName(homeClubName);
                    });
                    teamBuilder.withTeamsColor(teamsColorBuilder -> {
                        teamsColorBuilder.withShirtColor1(homeShirtColor1);
                        teamsColorBuilder.withShirtColor2(homeShirtColor2);
                        teamsColorBuilder.withGoalkeeperColor1(homeGoalkeeperColor1);
                        teamsColorBuilder.withGoalkeeperColor2(homeGoalkeeperColor2);
                    });
                    teamBuilder.withCoach(coachBuilder -> {
                        coachBuilder.withId(homeCoachId);
                        coachBuilder.withName(homeCoachName);
                        coachBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(homeCoachPhoneNumber));
                    });
                })
                .withVisitingTeam(teamBuilder -> {
                    teamBuilder.withId(visitingTeamId);
                    teamBuilder.withCategory(categoryBuilder -> {
                        categoryBuilder.withAge(18).withIsMaxAge(false);
                    });
                    teamBuilder.withGender(visitingGender);
                    teamBuilder.withNumber(visitingNumber);
                    teamBuilder.withClub(clubBuilder -> {
                        clubBuilder.withCode(visitingClubCode);
                        clubBuilder.withName(visitingClubName);
                    });
                    teamBuilder.withTeamsColor(teamsColorBuilder -> {
                        teamsColorBuilder.withShirtColor1(visitingShirtColor1);
                        teamsColorBuilder.withShirtColor2(visitingShirtColor2);
                        teamsColorBuilder.withGoalkeeperColor1(visitingGoalkeeperColor1);
                        teamsColorBuilder.withGoalkeeperColor2(visitingGoalkeeperColor2);
                    });
                    teamBuilder.withCoach(coachBuilder -> {
                        coachBuilder.withId(visitingCoachId);
                        coachBuilder.withName(visitingCoachName);
                        coachBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(visitingCoachPhoneNumber));
                    });
                })
                .withScore(scoreBuilder -> {
                    scoreBuilder.withHomeScore(0);
                    scoreBuilder.withVisitingScore(0);
                })
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl(fdmeUrl))
                .withDate(date)
                .withTime(time)
                .build();

        // Assertions to validate the game object
        assertEquals("game123", game.getCode());
        assertEquals(competitionName, game.getCompetition().name());
        assertEquals(poolNumber, game.getCompetition().pool().code());
        assertEquals(poolName, game.getCompetition().pool().name());

        assertEquals(year, game.getWeek().year());
        assertEquals(week, game.getWeek().week());
        assertEquals(seasonName, game.getSeason().name());
        assertEquals(startDateSeason, game.getSeason().startDate());
        assertEquals(endDateSeason, game.getSeason().endDate());
        assertEquals(dayNumber, game.getDay().number());
        assertEquals(halleId, game.getHalle().id());
        assertEquals(halleName, game.getHalle().name());
        assertEquals(addressStreet, game.getHalle().address().street());
        assertEquals(addressPostalCode, game.getHalle().address().postalCode());
        assertEquals(addressCity, game.getHalle().address().city());
        assertEquals(glueAuthorization, game.getHalle().glueAuthorization());
        assertEquals(referee1Id, game.getReferees().designatedReferee1().id());
        assertEquals(referee1Name, game.getReferees().designatedReferee1().name());
        assertEquals(referee2Id, game.getReferees().designatedReferee2().id());
        assertEquals(referee2Name, game.getReferees().designatedReferee2().name());
        assertEquals(referee3Id, game.getReferees().officiatingReferee1().id());
        assertEquals(referee3Name, game.getReferees().officiatingReferee1().name());
        assertEquals(referee4Id, game.getReferees().officiatingReferee2().id());
        assertEquals(referee4Name, game.getReferees().officiatingReferee2().name());
        assertEquals(homeTeamId, game.getHomeTeam().getId());
        assertEquals(homeCategoryName, game.getHomeTeam().getCategory().name());
        assertEquals(homeGender, game.getHomeTeam().getGender());
        assertEquals(homeNumber, game.getHomeTeam().getNumber());
        assertEquals(homeClubCode, game.getHomeTeam().getClub().code());
        assertEquals(homeClubName, game.getHomeTeam().getClub().name());
        assertEquals(homeShirtColor1, game.getHomeTeam().getTeamsColor().shirtColor1());
        assertEquals(homeShirtColor2, game.getHomeTeam().getTeamsColor().shirtColor2());
        assertEquals(homeGoalkeeperColor1, game.getHomeTeam().getTeamsColor().goalkeeperColor1());
        assertEquals(homeGoalkeeperColor2, game.getHomeTeam().getTeamsColor().goalkeeperColor2());
        assertEquals(homeCoachId, game.getHomeTeam().getCoach().id());
        assertEquals(homeCoachName, game.getHomeTeam().getCoach().name());
        assertEquals(homeCoachPhoneNumber, game.getHomeTeam().getCoach().phoneNumber().phoneNumber());
        assertEquals(visitingTeamId, game.getVisitingTeam().getId());
        assertEquals(visitingCategoryName, game.getVisitingTeam().getCategory().name());
        assertEquals(visitingGender, game.getVisitingTeam().getGender());
        assertEquals(visitingNumber, game.getVisitingTeam().getNumber());
        assertEquals(visitingClubCode, game.getVisitingTeam().getClub().code());
        assertEquals(visitingClubName, game.getVisitingTeam().getClub().name());
        assertEquals(visitingShirtColor1, game.getVisitingTeam().getTeamsColor().shirtColor1());
        assertEquals(visitingShirtColor2, game.getVisitingTeam().getTeamsColor().shirtColor2());
        assertEquals(visitingGoalkeeperColor1, game.getVisitingTeam().getTeamsColor().goalkeeperColor1());
        assertEquals(visitingGoalkeeperColor2, game.getVisitingTeam().getTeamsColor().goalkeeperColor2());
        assertEquals(visitingCoachId, game.getVisitingTeam().getCoach().id());
        assertEquals(visitingCoachName, game.getVisitingTeam().getCoach().name());
        assertEquals(visitingCoachPhoneNumber, game.getVisitingTeam().getCoach().phoneNumber().phoneNumber());
        assertEquals(Score.DEFAULT, game.getScore());
        assertEquals(fdmeUrl, game.getFdme().url());
        assertEquals(date, game.getDate());
        assertEquals(time, game.getTime());
    }
}

