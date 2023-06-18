package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.builder.AddressBuilder;
import fr.hoenheimsports.gamedomain.builder.GameBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

public class GameRepositoryInMemory implements GameRepository, CoachRepository, HalleRepository, RefereeRepository, TeamRepository {
    private final Map<String,Game> games = new HashMap<>();

    public GameRepositoryInMemory() {
        this.populateStub();
    }

    @Override
    public Game save(Game game) {
        this.games.put(game.getCode(),game);
        return game;
    }


    public void clear() {
        this.games.clear();
    }

    @Override
    public Game findById(String gameCode) {
        return this.games.get(gameCode);
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(this.games.values());
    }

    @Override
    public void update(Game game) {
        this.games.put(game.getCode(),game);
    }

    @Override
    public void delete(String gameCode) {
        this.games.remove(gameCode);
    }

    @Override
    public Optional<Coach> findCoachByKeys(String name) {
        return this.games.values().stream()
                .flatMap(game -> Stream.of(game.getHomeTeam().getCoach(),game.getVisitingTeam().getCoach()))
                .filter(coach -> coach != null && coach.name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Halle> findHallByKeys(String name, String street, int cp, String city) {
        Address address = AddressBuilder.builder().withStreet(street).withPostalCode(cp).withCity(city).build();
        return this.games.values().stream()
                .map(Game::getHalle)
                .filter(halle -> halle != null && halle.name().equals(name) && halle.address().equals(address))
                .findFirst();
    }

    @Override
    public Optional<Referee> findRefereeByKeys(String name) {
        return this.games.values().stream()
                .flatMap(game -> Stream.of(game.getReferees().designatedReferee1(),game.getReferees().designatedReferee2(),game.getReferees().officiatingReferee1(),game.getReferees().officiatingReferee2()))
                .filter(referee -> referee != null && referee.name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Team> findTeamByKeys(Club club, Gender gender, Category category, int number) {
        return this.games.values().stream()
                .flatMap(game -> Stream.of(game.getHomeTeam(),game.getVisitingTeam()))
                .filter(team -> team != null && team.getClub().equals(club) && team.getGender().equals(gender) && team.getCategory().equals(category) && team.getNumber() == number)
                .findFirst();
    }
    private void populateStub() {
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
        Game game2 = GameBuilder.builder()
                .withCode("code game 2")
                .withDate(LocalDate.of(2023, 6, 30))
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
                                .withName("Club 3"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 3"))
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
                                .withName("Club 4"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 4"))
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
                                .withName("Club 5"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 5"))
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
                                .withName("Club 6"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 6"))
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
                                .withName("Club 7"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 7"))
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
                                .withName("Club 8"))
                        .withCategory(categoryBuilder -> categoryBuilder
                                .withName("categorie 8"))
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
        this.games.put(game1.getCode(),game1);
        this.games.put(game2.getCode(),game2);
        this.games.put(game3.getCode(),game3);
        this.games.put(game4.getCode(),game4);
    }
}
