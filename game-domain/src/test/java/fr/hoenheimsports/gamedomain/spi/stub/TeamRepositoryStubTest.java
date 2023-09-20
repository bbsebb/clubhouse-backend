package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeamRepositoryStubTest {

    private TeamRepositoryStub teamRepositoryStub;

    @BeforeEach
    void setUp() {
        teamRepositoryStub = new TeamRepositoryStub();
    }

    @Test
    void testSave() {
        Team team = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
        Team savedTeam = teamRepositoryStub.save(team);
        assertEquals(team, savedTeam, "The saved team should match the original team");
    }

    @Test
    void testFindById() {
        Team team = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
        teamRepositoryStub.save(team);
        Optional<Team> foundTeam = teamRepositoryStub.findById(team.getId().toString());

        assertTrue(foundTeam.isPresent(), "Team should be found by ID");
        assertEquals(team, foundTeam.get(), "Found team should match the saved team");
    }

    @Test
    void testFindTeamByKeys() {
        Team team = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
        teamRepositoryStub.save(team);
        Optional<Team> foundTeam = teamRepositoryStub.findByKeys(Club.UNKNOWN, Gender.UNKNOWN, Category.UNKNOWN, 1);

        assertTrue(foundTeam.isPresent(), "Team should be found by keys");
        assertEquals(team, foundTeam.get(), "Found team should match the saved team");
    }

    @Test
    void testFindAllTeam() {
        Team team1 = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
        Team team2 = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.UNKNOWN, 2, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);

        teamRepositoryStub.save(team1);
        teamRepositoryStub.save(team2);

        List<Team> teams = teamRepositoryStub.findAll();
        assertTrue(teams.contains(team1), "Teams list should contain the first saved team");
        assertTrue(teams.contains(team2), "Teams list should contain the second saved team");
    }

    @Test
    void testFindByCategoryAndGender() {
        Team team1 = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
        Team team2 = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.FEMALE, 2, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);

        teamRepositoryStub.save(team1);
        teamRepositoryStub.save(team2);

        List<Team> maleTeams = teamRepositoryStub.findByCategoryAndGender(Category.UNKNOWN.name(), Gender.MALE.name());
        assertTrue(maleTeams.contains(team1), "Male teams list should contain the first saved team");
        assertFalse(maleTeams.contains(team2), "Male teams list should not contain the second saved team");
    }
}
