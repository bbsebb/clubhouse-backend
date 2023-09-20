package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {
    private final UUID validId = UUID.randomUUID();
    private final Category validCategory = Category.UNKNOWN;
    private final Gender validGender = Gender.UNKNOWN;
    private final int validNumber = 1;
    private final Club validClub = Club.UNKNOWN;
    private final TeamsColor validColor = TeamsColor.UNKNOWN;
    private final Coach validCoach = Coach.UNKNOWN;

    @Test
    public void testConstructorValidInput() {
        Team team = new Team(validId, validCategory, validGender, validNumber, validClub, validColor, validCoach);

        assertEquals(validId, team.getId());
        assertEquals(validCategory, team.getCategory());
        assertEquals(validGender, team.getGender());
        assertEquals(validNumber, team.getNumber());
        assertEquals(validClub, team.getClub());
        assertEquals(validColor, team.getTeamsColor());
        assertEquals(validCoach, team.getCoach());
    }

    @Test
    public void testConstructorNullId() {
        assertThrows(NullPointerException.class, () -> new Team(null, validCategory, validGender, validNumber, validClub, validColor, validCoach));
    }

    @Test
    public void testConstructorNullCategory() {
        assertThrows(NullPointerException.class, () -> new Team(validId, null, validGender, validNumber, validClub, validColor, validCoach));
    }

    @Test
    public void testConstructorNullGender() {
        assertThrows(NullPointerException.class, () -> new Team(validId, validCategory, null, validNumber, validClub, validColor, validCoach));
    }

    @Test
    public void testConstructorInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Team(validId, validCategory, validGender, 0, validClub, validColor, validCoach));
    }

    @Test
    public void testConstructorNullClub() {
        assertThrows(NullPointerException.class, () -> new Team(validId, validCategory, validGender, validNumber, null, validColor, validCoach));
    }

    @Test
    public void testConstructorNullColor() {
        assertThrows(NullPointerException.class, () -> new Team(validId, validCategory, validGender, validNumber, validClub, null, validCoach));
    }

    @Test
    public void testConstructorNullCoach() {
        assertThrows(NullPointerException.class, () -> new Team(validId, validCategory, validGender, validNumber, validClub, validColor, null));
    }

    @Test
    public void testSetters() {
        Team team = new Team(validId, validCategory, validGender, validNumber, validClub, validColor, validCoach);

        TeamsColor newColor = TeamsColor.UNKNOWN;
        Coach newCoach = new Coach(UUID.randomUUID(), "New Coach", PhoneNumber.UNKNOWN);

        team.setTeamsColor(newColor);
        team.setCoach(newCoach);

        assertEquals(newColor, team.getTeamsColor());
        assertEquals(newCoach, team.getCoach());
    }
}