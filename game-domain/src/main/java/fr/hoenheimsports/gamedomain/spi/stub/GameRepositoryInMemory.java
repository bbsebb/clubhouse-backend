package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.builder.AddressBuilder;
import fr.hoenheimsports.gamedomain.model.*;
import fr.hoenheimsports.gamedomain.spi.*;

import java.util.*;
import java.util.stream.Stream;

public class GameRepositoryInMemory implements GameRepository, CoachRepository, HalleRepository, RefereeRepository, TeamRepository {
    private final Map<String,Game> games = new HashMap<>();

    public GameRepositoryInMemory() {
    }

    @Override
    public Game save(Game game) {
        this.games.put(game.getCode(),game);
        return game;
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
    public void populate(List<Game> games) {
        games.forEach(game -> this.games.put(game.getCode(),game));
    }


}
