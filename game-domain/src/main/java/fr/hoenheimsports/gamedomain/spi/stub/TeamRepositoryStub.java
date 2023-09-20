package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Gender;
import fr.hoenheimsports.gamedomain.model.Team;
import fr.hoenheimsports.gamedomain.spi.TeamRepository;

import java.util.*;
@Stub
public class TeamRepositoryStub implements TeamRepository {
    private final Map<UUID,Team> teams = new HashMap<>();
    @Override
    public Optional<Team> findByKeys(Club club, Gender gender, Category category, int number) {
        return this.teams.values().stream()
                .filter(team -> team.getClub().equals(club) && team.getGender().equals(gender) && team.getCategory().equals(category) && team.getNumber() == number)
                .findFirst();
    }

    @Override
    public List<Team> findAll() {
        return this.teams.values().stream().toList();
    }

    @Override
    public List<Team> findByCategoryAndGender(String categoryName, String genderName) {
        return this.teams.values().stream()
                .filter(team -> team.getCategory().name().equals(categoryName) && team.getGender().equals(Gender.valueOf(genderName)))
                .toList();
    }

    @Override
    public Optional<Team> findById(String id) {
        return Optional.ofNullable(this.teams.get(UUID.fromString(id)));
    }

    @Override
    public Team save(Team team) {
        this.teams.put(team.getId(),team);
        return team;
    }
}
