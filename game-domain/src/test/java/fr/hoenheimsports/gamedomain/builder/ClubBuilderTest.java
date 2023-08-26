package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubBuilderTest {
    @Test
    public void testBuilderMethod() {
        ClubBuilder clubBuilder = ClubBuilder.builder();
        assertNotNull(clubBuilder);
    }
    @Test
    public void testClubBuilder() {
        ClubBuilder clubBuilder = new ClubBuilder();
        String expectedCode = "0167028";
        String expectedName = "Hoenheim";

        Club club = clubBuilder
                .withCode(expectedCode)
                .withName(expectedName)
                .addHalle(Hall.UNKNOWN)
                .build();

        assertEquals(expectedCode, club.code());
        assertEquals(expectedName, club.name());
        assertTrue(club.halles().contains(Hall.UNKNOWN));
    }
}