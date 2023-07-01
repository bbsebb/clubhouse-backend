package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeamBuilderTest {
    private TeamBuilder teamBuilder;
    private UUID expectedId;
    private Category expectedCategory;
    private Gender expectedGender;
    private int expectedNumber;
    private Club expectedClub;
    private TeamsColor expectedTeamsColor;
    private Coach expectedCoach;
    private GameRepositoryInMemory gameRepositoryInMemory;
    private UUID expectedIdInMemory;
    private Category expectedCategoryInMemory;
    private Gender expectedGenderInMemory;
    private int expectedNumberInMemory;
    private Club expectedClubInMemory;
    private TeamsColor expectedTeamsColorInMemory;
    private Coach expectedCoachInMemory;

    @BeforeEach
    public void setup() {
        teamBuilder = TeamBuilder.builder();
        expectedId = UUID.randomUUID();
        expectedCategory = new Category("Category A");
        expectedGender = Gender.MALE;
        expectedNumber = 1;
        expectedClub = new Club("Club A", "ABC");
        expectedTeamsColor = new TeamsColor(
                TeamColor.BLUE,
                TeamColor.WHITE,
                TeamColor.RED,
                TeamColor.YELLOW
        );
        expectedCoach = new Coach(UUID.randomUUID(), "Coach A", new PhoneNumber("555555555"));

        expectedIdInMemory = UUID.randomUUID();
        expectedCategoryInMemory = new Category("Category 1");
        expectedGenderInMemory = Gender.MALE;
        expectedNumberInMemory = 1;
        expectedClubInMemory = new Club("Code 1", "Club 1");
        expectedTeamsColorInMemory = new TeamsColor(
                TeamColor.RED,
                TeamColor.BLUE,
                TeamColor.BEIGE,
                TeamColor.GARNET
        );
        expectedCoachInMemory = new Coach(UUID.randomUUID(), "Coach 1", new PhoneNumber("1"));

        gameRepositoryInMemory = new GameRepositoryInMemory();
        gameRepositoryInMemory.save(
                GameBuilder.builder()
                        .withCompetition(Competition.UNKNOWN)
                        .withCode("code test")
                        .withDay(Day.SINGLE_DAY_GAME)
                        .withWeek(Week.NOW)
                        .withSeason(Season.SEASON_2022_2023)
                        .withFDME(FDME.UNKNOWN)
                        .withHalle(Halle.UNKNOWN)
                        .withHomeTeam(homeTeamBuilder -> homeTeamBuilder
                                .withId(expectedIdInMemory)
                                .withClub(expectedClubInMemory)
                                .withGender(expectedGenderInMemory)
                                .withTeamsColor(expectedTeamsColorInMemory)
                                .withCategory(expectedCategoryInMemory)
                                .withNumber(expectedNumberInMemory)
                                .withCoach(expectedCoachInMemory))
                        .withVisitingTeam(Team.UNKNOWN)
                        .withReferees(Referees.UNKNOWN)
                        .build()

        );
    }

    @Test
    public void testBuilderMethod() {
        assertNotNull(teamBuilder);
    }

    @Test
    public void testBuild() {
        Team team = teamBuilder
                .withId(expectedId)
                .withCategory(builder -> builder
                        .withName(expectedCategory.name())
                )
                .withGender(expectedGender)
                .withNumber(expectedNumber)
                .withClub(builder -> builder
                        .withName(expectedClub.name())
                        .withCode(expectedClub.code())
                )
                .withTeamsColor(builder -> builder
                        .withShirtColor1(colorBuilder -> colorBuilder.withFrenchName(expectedTeamsColor.shirtColor1().getFrenchName()))
                        .withShirtColor2(colorBuilder -> colorBuilder.withFrenchName(expectedTeamsColor.shirtColor2().getFrenchName()))
                        .withGoalkeeperColor1(colorBuilder -> colorBuilder.withFrenchName(expectedTeamsColor.goalkeeperColor1().getFrenchName()))
                        .withGoalkeeperColor2(colorBuilder -> colorBuilder.withFrenchName(expectedTeamsColor.goalkeeperColor2().getFrenchName()))
                )
                .withCoach(builder -> builder
                        .withId(expectedCoach.id())
                        .withName(expectedCoach.name())
                        .withPhoneNumber(expectedCoach.phoneNumber())
                )
                .build();

        assertEquals(expectedId, team.getId());
        assertEquals(expectedCategory, team.getCategory());
        assertEquals(expectedGender, team.getGender());
        assertEquals(expectedNumber, team.getNumber());
        assertEquals(expectedClub, team.getClub());
        assertEquals(expectedTeamsColor, team.getTeamsColor());
        assertEquals(expectedCoach, team.getCoach());
    }

    @Test
    public void testBuildWithObject() {
        Team team = teamBuilder
                .withId(expectedId)
                .withCategory(expectedCategory)
                .withGender(expectedGender)
                .withNumber(expectedNumber)
                .withClub(expectedClub)
                .withTeamsColor(expectedTeamsColor)
                .withCoach(expectedCoach)
                .build();

        assertEquals(expectedId, team.getId());
        assertEquals(expectedCategory, team.getCategory());
        assertEquals(expectedGender, team.getGender());
        assertEquals(expectedNumber, team.getNumber());
        assertEquals(expectedClub, team.getClub());
        assertEquals(expectedTeamsColor, team.getTeamsColor());
        assertEquals(expectedCoach, team.getCoach());
    }

    @Test
    public void testBuildWithAutogeneratedId() {
        Team team = teamBuilder
                .withCategory(expectedCategory)
                .withGender(expectedGender)
                .withNumber(expectedNumber)
                .withClub(expectedClub)
                .withTeamsColor(expectedTeamsColor)
                .withCoach(expectedCoach)
                .build();

        assertNotNull(team.getId());
        assertEquals(expectedCategory, team.getCategory());
        assertEquals(expectedGender, team.getGender());
        assertEquals(expectedNumber, team.getNumber());
        assertEquals(expectedClub, team.getClub());
        assertEquals(expectedTeamsColor, team.getTeamsColor());
        assertEquals(expectedCoach, team.getCoach());
    }

    @Test
    public void testCoachBuilderWithRetrievedId() {
        Team team = teamBuilder
                .withCategory(expectedCategoryInMemory)
                .withGender(expectedGenderInMemory)
                .withNumber(expectedNumberInMemory)
                .withClub(expectedClubInMemory)
                .withTeamsColor(expectedTeamsColor)
                .withCoach(expectedCoach)
                .addIdGeneratorFromRepository(gameRepositoryInMemory)
                .build();

        assertNotNull(team.getId());
        assertEquals(expectedIdInMemory, team.getId());
        assertEquals(expectedCategoryInMemory, team.getCategory());
        assertEquals(expectedGenderInMemory, team.getGender());
        assertEquals(expectedNumberInMemory, team.getNumber());
        assertEquals(expectedClubInMemory, team.getClub());
        assertEquals(expectedTeamsColor, team.getTeamsColor());
        assertEquals(expectedCoach, team.getCoach());
    }
    @Test
    public void testCoachBuilderWithRetrievedIdWithExistingId() {
        Team team = teamBuilder
                .withId(expectedId)
                .withCategory(expectedCategoryInMemory)
                .withGender(expectedGenderInMemory)
                .withNumber(expectedNumberInMemory)
                .withClub(expectedClubInMemory)
                .withTeamsColor(expectedTeamsColor)
                .withCoach(expectedCoach)
                .addIdGeneratorFromRepository(gameRepositoryInMemory)
                .build();

        assertNotNull(team.getId());
        assertEquals(expectedIdInMemory, team.getId());
        assertNotEquals(expectedId, team.getId());
        assertEquals(expectedCategoryInMemory, team.getCategory());
        assertEquals(expectedGenderInMemory, team.getGender());
        assertEquals(expectedNumberInMemory, team.getNumber());
        assertEquals(expectedClubInMemory, team.getClub());
        assertEquals(expectedTeamsColor, team.getTeamsColor());
        assertEquals(expectedCoach, team.getCoach());
    }
}