package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompetitionTest {

    @Test
    void testValidCompetitionCreation() {
        Competition competition = new Competition("Champion's League");
        assertEquals("Champion's League", competition.name());
    }

    @Test
    void testUnknownCompetition() {
        Competition competition = Competition.UNKNOWN;
        assertEquals("unknown", competition.name());
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new Competition(null);
        });
    }
}