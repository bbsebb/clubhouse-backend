package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Stub
public class KitOfGames {
    public static List<Game> getKitOfGames() {
        List<Game> games = new ArrayList<>();
        Game game1 = GameBuilder.builder()
                .withCode("code game 1")
                .withDate(LocalDate.now())
                .withWeek(Week.NOW)
                .withSeason(Season.SEASON_2022_2023)
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
                                .withName("Club 1")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
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
                                .withName("Club 2")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
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
        Game game2 = GameBuilder.builder()
                .withCode("code game 2")
                .withDate(LocalDate.of(2023, 6, 30))
                .withWeek(Week.NOW)
                .withSeason(Season.SEASON_2022_2023)
                .withTime(LocalTime.of(14, 30))
                .withDay(dayBuilder -> dayBuilder.withNumber(2).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 2")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool2")
                                .withCode("code Pool 2")))
                .withHalle(halleBuilder -> halleBuilder
                        .withId(UUID.randomUUID())
                        .withName("Halle 2")
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet("rue 2")
                                .withPostalCode(68000)
                                .withCity("ville 2"))
                        .withGlueAuthorization(GlueAuthorization.UNAUTHORIZED))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 3")
                                .withName("Club 3")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.FEMALE)
                        .withNumber(2)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.YELLOW)
                                .withGoalkeeperColor2(TeamColor.GREEN)
                                .withShirtColor1(TeamColor.PURPLE)
                                .withShirtColor2(TeamColor.WHITE))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach3")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("1111111111"))))
                .withVisitingTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 4")
                                .withName("Club 4")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.MALE)
                        .withNumber(2)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.RED)
                                .withGoalkeeperColor2(TeamColor.BLUE)
                                .withShirtColor1(TeamColor.GREEN)
                                .withShirtColor2(TeamColor.YELLOW))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach4")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("2222222222"))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(8)
                        .withVisitingScore(3))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 3"))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 4"))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 3"))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 4")))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://example.com"))
                .build();
        Game game3 = GameBuilder.builder()
                .withCode("code game 3")
                .withDate(LocalDate.of(2023, 7, 15))
                .withTime(LocalTime.of(16, 0))
                .withDay(dayBuilder -> dayBuilder.withNumber(3).build())
                .withWeek(Week.NOW)
                .withSeason(Season.SEASON_2022_2023)
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 3")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool3")
                                .withCode("code Pool 3")))
                .withHalle(halleBuilder -> halleBuilder
                        .withId(UUID.randomUUID())
                        .withName("Halle 3")
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet("rue 3")
                                .withPostalCode(69000)
                                .withCity("ville 3"))
                        .withGlueAuthorization(GlueAuthorization.AUTHORIZED))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 5")
                                .withName("Club 5")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.MALE)
                        .withNumber(3)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.YELLOW)
                                .withGoalkeeperColor2(TeamColor.GREEN)
                                .withShirtColor1(TeamColor.PURPLE)
                                .withShirtColor2(TeamColor.WHITE))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach6")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("3333333333"))))
                .withVisitingTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 6")
                                .withName("Club 6")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.FEMALE)
                        .withNumber(3)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.RED)
                                .withGoalkeeperColor2(TeamColor.BLUE)
                                .withShirtColor1(TeamColor.GREEN)
                                .withShirtColor2(TeamColor.YELLOW))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach7")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("4444444444"))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(2)
                        .withVisitingScore(4))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 5"))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 6"))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 5"))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 6")))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://example.com"))
                .build();

        Game game4 = GameBuilder.builder()
                .withCode("code game 4")
                .withDate(LocalDate.of(2023, 7, 20))
                .withTime(LocalTime.of(18, 30))
                .withDay(dayBuilder -> dayBuilder.withNumber(4).build())
                .withWeek(Week.NOW)
                .withSeason(Season.SEASON_2022_2023)
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 4")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool4")
                                .withCode("code Pool 4")))
                .withHalle(halleBuilder -> halleBuilder
                        .withId(UUID.randomUUID())
                        .withName("Halle 4")
                        .withAddress(addressBuilder -> addressBuilder
                                .withStreet("rue 4")
                                .withPostalCode(68000)
                                .withCity("ville 4"))
                        .withGlueAuthorization(GlueAuthorization.UNAUTHORIZED))
                .withHomeTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 7")
                                .withName("Club 7")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.MIXED)
                        .withNumber(4)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.WHITE)
                                .withGoalkeeperColor2(TeamColor.YELLOW)
                                .withShirtColor1(TeamColor.BLUE)
                                .withShirtColor2(TeamColor.GREEN))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach8")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("5555555555"))))
                .withVisitingTeam(teamBuilder -> teamBuilder
                        .withId(UUID.randomUUID())
                        .withClub(clubBuilder -> clubBuilder
                                .withCode("Code club 8")
                                .withName("Club 8")
                                .addHalle(Hall.UNKNOWN))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withAge(10)
                                .withIsMaxAge(true))
                        .withGender(Gender.UNKNOWN)
                        .withNumber(4)
                        .withTeamsColor(teamsColorBuilder -> teamsColorBuilder
                                .withGoalkeeperColor1(TeamColor.RED)
                                .withGoalkeeperColor2(TeamColor.YELLOW)
                                .withShirtColor1(TeamColor.PURPLE)
                                .withShirtColor2(TeamColor.WHITE))
                        .withCoach(coachBuilder -> coachBuilder
                                .withId(UUID.randomUUID())
                                .withName("coach9")
                                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder
                                        .withPhoneNumber("6666666666"))))
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(0)
                        .withVisitingScore(0))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 7"))
                        .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("designated referee 8"))
                        .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 7"))
                        .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                                .withId(UUID.randomUUID())
                                .withName("officiating referee 8")))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://example.com"))
                .build();
        games.add(game1);
        games.add(game2);
        games.add(game3);
        games.add(game4);

        return games;
    }
}
