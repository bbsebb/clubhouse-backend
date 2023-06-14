package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Club;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubBuilderTest {
    @Test
    public void testClubBuilder() {
        ClubBuilder clubBuilder = new ClubBuilder();
        String expectedCode = "0167028";
        String expectedName = "Hoenheim";

        Club club = clubBuilder
                .withCode(expectedCode)
                .withName(expectedName)
                .build();

        assertEquals(expectedCode, club.code());
        assertEquals(expectedName, club.name());
    }
}