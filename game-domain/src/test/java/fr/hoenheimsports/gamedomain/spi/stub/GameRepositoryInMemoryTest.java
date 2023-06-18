package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.builder.*;
import fr.hoenheimsports.gamedomain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryInMemoryTest {
    private GameRepositoryInMemory gameRepository;
    private Game game1;
    private Game game2;
    private Coach coach;
    private Halle halle;
    private Referee referee;
    private Team homeTeam;
    private Team visitingTeam;

    @BeforeEach
    void setup() {
        gameRepository = new GameRepositoryInMemory();
        // Create coach
        coach = CoachBuilder.builder()
                .withId(UUID.randomUUID())
                .withName("Coach A")
                .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber("0000000000"))
                .build();

        // Create halle
        halle = HalleBuilder.builder()
                .withId(UUID.randomUUID())
                .withName("Halle 1")
                .withAddress(addressBuilder -> addressBuilder
                        .withStreet("rue 1")
                        .withPostalCode(67000)
                        .withCity("ville 1"))
                .withGlueAuthorization(GlueAuthorization.AUTHORIZED)
                .build();

        // Create referee
        referee = RefereeBuilder.builder()
                .withId(UUID.randomUUID())
                .withName("Referee 1")
                .build();

        // Create home team
        homeTeam = TeamBuilder.builder()
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
                .withCoach(coach)
                .build();

        // Create visiting team
        visitingTeam = TeamBuilder.builder()
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
                .withCoach(coach)
                .build();

        // Create game 1
        game1 = GameBuilder.builder()
                .withCode("game1")
                .withDate(LocalDate.now())
                .withTime(LocalTime.now())
                .withDay(dayBuilder -> dayBuilder.withNumber(1).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 1")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool1")
                                .withCode("code Pool 1")))
                .withHalle(halle)
                .withHomeTeam(homeTeam)
                .withVisitingTeam(visitingTeam)
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(5)
                        .withVisitingScore(10))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(referee)
                        .withDesignatedReferee2(referee)
                        .withOfficiatingReferee1(referee)
                        .withOfficiatingReferee2(referee))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://hoenheimsports.fr"))
                .build();

        // Create game 2
        game2 = GameBuilder.builder()
                .withCode("game2")
                .withDate(LocalDate.now())
                .withTime(LocalTime.now())
                .withDay(dayBuilder -> dayBuilder.withNumber(2).build())
                .withCompetition(competitionBuilder -> competitionBuilder
                        .withName("Competition 2")
                        .withPool(poolBuilder -> poolBuilder
                                .withName("Pool2")
                                .withCode("code Pool 2")))
                .withHalle(halle)
                .withHomeTeam(homeTeam)
                .withVisitingTeam(visitingTeam)
                .withScore(scoreBuilder -> scoreBuilder
                        .withHomeScore(3)
                        .withVisitingScore(7))
                .withReferees(refereesBuilder -> refereesBuilder
                        .withDesignatedReferee1(referee)
                        .withDesignatedReferee2(referee)
                        .withOfficiatingReferee1(referee)
                        .withOfficiatingReferee2(referee))
                .withFDME(fdmeBuilder -> fdmeBuilder.withUrl("https://hoenheimsports.fr"))
                .build();
        gameRepository.save(game1);
        gameRepository.save(game2);
    }
    @Test
    void testSave() {
        assertEquals(game1, gameRepository.findById(game1.getCode()));
        assertEquals(game2, gameRepository.findById(game2.getCode()));
    }

    @Test
    void testFindById() {
        assertEquals(game1, gameRepository.findById(game1.getCode()));
        assertEquals(game2, gameRepository.findById(game2.getCode()));
        assertEquals(2, gameRepository.findAll().size());
    }

    @Test
    void testFindAll() {
        List<Game> games = gameRepository.findAll();
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }

    @Test
    void testUpdate() {

        Score updatedScore = new Score(21,25);
        Game updatedGame = GameBuilder.builder()
                .withCode(game1.getCode())
                .withCompetition(game1.getCompetition())
                .withDay(game1.getDay())
                .withDate(game1.getDate())
                .withTime(game1.getTime())
                .withFDME(game1.getFdme())
                .withHalle(game1.getHalle())
                .withHomeTeam(game1.getHomeTeam())
                .withVisitingTeam(game1.getVisitingTeam())
                .withScore(updatedScore)
                .withReferees(game1.getReferees())
                .build();

        gameRepository.update(updatedGame);

        assertEquals(updatedGame, gameRepository.findById(game1.getCode()));
        assertEquals(game2, gameRepository.findById(game2.getCode()));
        assertEquals(updatedScore, gameRepository.findById(game1.getCode()).getScore());
    }

    @Test
    void testDelete() {
        gameRepository.delete(game1.getCode());
        assertNull(gameRepository.findById(game1.getCode()));
        assertEquals(game2, gameRepository.findById(game2.getCode()));
    }

    @Test
    void testFindTeamByKeys() {
        Optional<Team> foundTeam = gameRepository.findTeamByKeys(homeTeam.getClub(), homeTeam.getGender(), homeTeam.getCategory(), homeTeam.getNumber());
        assertTrue(foundTeam.isPresent());
        assertEquals(homeTeam, foundTeam.get());
    }

    @Test
    void testFindRefereeByKeys() {
        Optional<Referee> foundReferee = gameRepository.findRefereeByKeys(referee.name());
        assertTrue(foundReferee.isPresent());
        assertEquals(referee, foundReferee.get());
    }

    @Test
    void testFindHallByKeys() {
        Optional<Halle> foundHalle = gameRepository.findHallByKeys(halle.name(), halle.address().street(), halle.address().postalCode(), halle.address().city());
        assertTrue(foundHalle.isPresent());
        assertEquals(halle, foundHalle.get());
    }

    @Test
    void testFindCoachByKeys() {
        Optional<Coach> foundCoach = gameRepository.findCoachByKeys(coach.name());
        assertTrue(foundCoach.isPresent());
        assertEquals(coach, foundCoach.get());
    }

}