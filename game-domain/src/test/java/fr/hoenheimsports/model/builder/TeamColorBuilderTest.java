package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.TeamColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamColorBuilderTest {
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