package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.TeamColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamColorBuilderTest {
    @Test
    public void testBuilderMethod() {
        TeamColorBuilder teamColorBuilder = TeamColorBuilder.builder();
        assertNotNull(teamColorBuilder);
    }
    @Test
    public void testTeamColorBuilder() {
        TeamColorBuilder teamColorBuilder = new TeamColorBuilder();
        String expectedFrenchName = "BLEU";

        TeamColor teamColor = teamColorBuilder
                .withFrenchName(expectedFrenchName)
                .build();

        assertEquals(TeamColor.BLUE, teamColor);
    }
}