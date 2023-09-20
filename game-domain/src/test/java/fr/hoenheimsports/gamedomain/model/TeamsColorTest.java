package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamsColorTest {
    @Test
    void testNonNullColors() {
        TeamsColor teamsColor = new TeamsColor(TeamColor.RED, TeamColor.BLUE, TeamColor.WHITE, TeamColor.GREEN);
        assertEquals(TeamColor.RED, teamsColor.shirtColor1());
        assertEquals(TeamColor.BLUE, teamsColor.shirtColor2());
        assertEquals(TeamColor.WHITE, teamsColor.goalkeeperColor1());
        assertEquals(TeamColor.GREEN, teamsColor.goalkeeperColor2());
    }

    @Test
    void testNullColors() {
        TeamsColor teamsColor = new TeamsColor(null, null, null, null);
        assertEquals(TeamColor.UNKNOWN, teamsColor.shirtColor1());
        assertEquals(TeamColor.UNKNOWN, teamsColor.shirtColor2());
        assertEquals(TeamColor.UNKNOWN, teamsColor.goalkeeperColor1());
        assertEquals(TeamColor.UNKNOWN, teamsColor.goalkeeperColor2());
    }
}