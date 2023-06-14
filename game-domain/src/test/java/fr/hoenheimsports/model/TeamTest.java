package fr.hoenheimsports.model;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Team(null, Category.UNKNOWN, Gender.MALE, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Team(UUID.randomUUID(), null, Gender.MALE, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Team(UUID.randomUUID(), Category.UNKNOWN, null, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN));
        assertThrows(IllegalArgumentException.class, () -> new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 0, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 1, null, TeamsColor.UNKNOWN, Coach.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 1, Club.UNKNOWN, null, Coach.UNKNOWN));
        assertThrows(NullPointerException.class, () -> new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, null));
    }


    @Test
    public void testSetTeamsColor() {
        Team team = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.MALE, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);

        team.setTeamsColor(TeamsColor.UNKNOWN);
        assertEquals(TeamsColor.UNKNOWN, team.teamsColor());

        assertThrows(NullPointerException.class, () -> team.setTeamsColor(null));
    }

    @Test
    public void testSetCoach() {
        Team team = new Team(UUID.randomUUID(), Category.UNKNOWN, Gender.FEMALE, 2, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);

        Coach coach = Coach.UNKNOWN;
        team.setCoach(coach);
        assertEquals(coach, team.coach());

        assertThrows(NullPointerException.class, () -> team.setCoach(null));
    }

    @Test
    public void testEquals() {
        UUID id = UUID.randomUUID();
        Category category = new Category("Category A");
        Gender gender = Gender.MALE;
        int number = 1;
        Club club = new Club("Club A", "ABC");
        TeamsColor teamsColor = new TeamsColor(
                TeamColor.BLUE,
                TeamColor.WHITE,
                TeamColor.RED,
                TeamColor.YELLOW
        );
        Coach coach = new Coach(UUID.randomUUID(), "Coach A", new PhoneNumber("555555555"));

        Team team1 = new Team(id, category, gender, number, club, teamsColor, coach);
        Team team2 = new Team(id, category, gender, number, club, teamsColor, coach);
        Team team3 = new Team(UUID.randomUUID(), category, gender, number, club, teamsColor, coach);

        assertTrue(team1.equals(team2));
        assertFalse(team1.equals(team3));
    }

    @Test
    public void testHashCode() {
        UUID id = UUID.randomUUID();
        Category category = new Category("Category A");
        Gender gender = Gender.MALE;
        int number = 1;
        Club club = new Club("Club A", "ABC");
        TeamsColor teamsColor = new TeamsColor(
                TeamColor.BLUE,
                TeamColor.WHITE,
                TeamColor.RED,
                TeamColor.YELLOW
        );
        Coach coach = new Coach(UUID.randomUUID(), "Coach A", new PhoneNumber("555555555"));

        Team team1 = new Team(id, category, gender, number, club, teamsColor, coach);
        Team team2 = new Team(id, category, gender, number, club, teamsColor, coach);

        assertEquals(team1.hashCode(), team2.hashCode());
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        Category category = new Category("Category A");
        Gender gender = Gender.MALE;
        int number = 1;
        Club club = new Club("Club A", "ABC");
        TeamsColor teamsColor = new TeamsColor(
                TeamColor.BLUE,
                TeamColor.WHITE,
                TeamColor.RED,
                TeamColor.YELLOW
        );
        Coach coach = new Coach(UUID.randomUUID(), "Coach A", new PhoneNumber("555555555"));

        Team team = new Team(id, category, gender, number, club, teamsColor, coach);

        String expected = "Team[id=" + id + ", category=" + category + ", gender=" + gender +
                ", number=" + number + ", club=" + club + ", teamsColor=" + teamsColor + ", coach=" + coach + ']';

        assertEquals(expected, team.toString());
    }
}