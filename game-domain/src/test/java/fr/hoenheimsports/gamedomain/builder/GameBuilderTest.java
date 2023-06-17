package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.*;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class GameBuilderTest {
    @Test
    void testGameBuilderWithObject() throws MalformedURLException {
        // Create necessary objects using builders
        Game gameExcepted = new Game(
                "game123",
                new Competition(
                        "Competition1",
                        List.of(new Pool(
                                "Pool1",
                                "Pool1"
                        ))
                ),
                new Day(1),
                new Halle(
                        UUID.randomUUID(),
                        "Halle1",
                        new Address("Street2", 67890, "City2"),
                        GlueAuthorization.UNKNOWN
                ),
                new Referees(
                        new Referee(UUID.randomUUID(), "Referee1", new PhoneNumber("1234567890")),
                        new Referee(UUID.randomUUID(), "Referee2", new PhoneNumber("0987654321")),
                        new Referee(UUID.randomUUID(), "Referee3", new PhoneNumber("0987654323")),
                        new Referee(UUID.randomUUID(), "Referee4", new PhoneNumber("0987654324"))
                ),
                new Team(
                        UUID.randomUUID(),
                        new Category("Category1"),
                        Gender.MALE,
                        1,
                        new Club("Club1", "ClubName1"),
                        new TeamsColor(TeamColor.BEIGE,TeamColor.BROWN,TeamColor.BLACK,TeamColor.BURGUNDY),
                        new Coach(UUID.randomUUID(), "Coach1", new PhoneNumber("1111111111"))

                ),
                new Team(
                        UUID.randomUUID(),
                        new Category("Category2"),
                        Gender.FEMALE,
                        5,
                        new Club("Club2", "ClubName2"),
                        new TeamsColor(TeamColor.BLACK,TeamColor.GARNET,TeamColor.NAVY_BLUE,TeamColor.GREY),
                        new Coach(UUID.randomUUID(), "Coach2", new PhoneNumber("2222222222"))
                ),
                Score.DEFAULT,
                new FDME(new URL("https://media-ffhb-fdm.ffhandball.fr/fdm/S/A/E/E/")),
                LocalDateTime.now()
        );

        // Build a Game object using GameBuilder
        Game game = new GameBuilder()
                .withCode(gameExcepted.getCode())
                .withCompetition(gameExcepted.getCompetition())
                .withDay(gameExcepted.getDay())
                .withHalle(gameExcepted.getHalle())
                .withReferees(gameExcepted.getReferees())
                .withHomeTeam(gameExcepted.getHomeTeam())
                .withVisitingTeam(gameExcepted.getVisitingTeam())
                .withScore(gameExcepted.getScore())
                .withFDME(gameExcepted.getFdme())
                .withDateTime(gameExcepted.getDateTime())
                .build();

        // Assertions to validate the game object
        assertEquals(gameExcepted.getCode(), game.getCode());
        assertEquals(gameExcepted.getCompetition(), game.getCompetition());
        assertEquals(gameExcepted.getDay(), game.getDay());
        assertEquals(gameExcepted.getHalle(), game.getHalle());
        assertEquals(gameExcepted.getReferees(), game.getReferees());
        assertEquals(gameExcepted.getHomeTeam(), game.getHomeTeam());
        assertEquals(gameExcepted.getVisitingTeam(), game.getVisitingTeam());
        assertEquals(gameExcepted.getScore(), game.getScore());
        assertEquals(gameExcepted.getFdme(), game.getFdme());
        assertEquals(gameExcepted.getDateTime(), game.getDateTime());
    }

    @Test
    void testGameBuilder() throws MalformedURLException {
        // Create necessary objects using builders
        UUID competitionId = UUID.randomUUID();
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
        String referee1PhoneNumber = "1234567890";
        UUID referee2Id = UUID.randomUUID();
        String referee2Name = "Referee2";
        String referee2PhoneNumber = "0987654321";
        UUID referee3Id = UUID.randomUUID();
        String referee3Name = "Referee3";
        String referee3PhoneNumber = "0987654323";
        UUID referee4Id = UUID.randomUUID();
        String referee4Name = "Referee4";
        String referee4PhoneNumber = "0987654324";
        UUID homeTeamId = UUID.randomUUID();
        UUID homeCategoryId = UUID.randomUUID();
        String homeCategoryName = "Category1";
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
        UUID visitingCategoryId = UUID.randomUUID();
        String visitingCategoryName = "Category2";
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

        Game game = new GameBuilder()
                .withCode("game123")
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
                        refereeBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(referee1PhoneNumber));
                    });
                    refereesBuilder.withDesignatedReferee2(refereeBuilder -> {
                        refereeBuilder.withId(referee2Id);
                        refereeBuilder.withName(referee2Name);
                        refereeBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(referee2PhoneNumber));
                    });
                    refereesBuilder.withOfficiatingReferee1(refereeBuilder -> {
                        refereeBuilder.withId(referee3Id);
                        refereeBuilder.withName(referee3Name);
                        refereeBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(referee3PhoneNumber));
                    });
                    refereesBuilder.withOfficiatingReferee2(refereeBuilder -> {
                        refereeBuilder.withId(referee4Id);
                        refereeBuilder.withName(referee4Name);
                        refereeBuilder.withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(referee4PhoneNumber));
                    });
                })
                .withHomeTeam(teamBuilder -> {
                    teamBuilder.withId(homeTeamId);
                    teamBuilder.withCategory(categoryBuilder -> {
                        categoryBuilder.withName(homeCategoryName);
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
                        categoryBuilder.withName(visitingCategoryName);
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
                .withDateTime(LocalDateTime.now())
                .build();

        // Assertions to validate the game object
        assertEquals("game123", game.getCode());
        assertEquals(competitionName, game.getCompetition().name());
        assertEquals(poolNumber, game.getCompetition().pools().get(0).code());
        assertEquals(poolName, game.getCompetition().pools().get(0).name());
        assertEquals(dayNumber, game.getDay().number());
        assertEquals(halleId, game.getHalle().id());
        assertEquals(halleName, game.getHalle().name());
        assertEquals(addressStreet, game.getHalle().address().street());
        assertEquals(addressPostalCode, game.getHalle().address().postalCode());
        assertEquals(addressCity, game.getHalle().address().city());
        assertEquals(glueAuthorization, game.getHalle().glueAuthorization());
        assertEquals(referee1Id, game.getReferees().designatedReferee1().id());
        assertEquals(referee1Name, game.getReferees().designatedReferee1().name());
        assertEquals(referee1PhoneNumber, game.getReferees().designatedReferee1().phoneNumber().phoneNumber());
        assertEquals(referee2Id, game.getReferees().designatedReferee2().id());
        assertEquals(referee2Name, game.getReferees().designatedReferee2().name());
        assertEquals(referee2PhoneNumber, game.getReferees().designatedReferee2().phoneNumber().phoneNumber());
        assertEquals(referee3Id, game.getReferees().officiatingReferee1().id());
        assertEquals(referee3Name, game.getReferees().officiatingReferee1().name());
        assertEquals(referee3PhoneNumber, game.getReferees().officiatingReferee1().phoneNumber().phoneNumber());
        assertEquals(referee4Id, game.getReferees().officiatingReferee2().id());
        assertEquals(referee4Name, game.getReferees().officiatingReferee2().name());
        assertEquals(referee4PhoneNumber, game.getReferees().officiatingReferee2().phoneNumber().phoneNumber());
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
        assertNotNull(game.getDateTime());
    }
}

