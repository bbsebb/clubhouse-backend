package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.TeamColor;
import fr.hoenheimsports.model.TeamsColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamsColorBuilderTest {
    @Test
    public void testTeamsColorBuilderWithObject() {
        TeamsColorBuilder teamsColorBuilder = new TeamsColorBuilder();
        TeamColor expectedShirtColor1 = TeamColor.BLUE;
        TeamColor expectedShirtColor2 = TeamColor.WHITE;
        TeamColor expectedGoalkeeperColor1 = TeamColor.GREEN;
        TeamColor expectedGoalkeeperColor2 = TeamColor.YELLOW;

        TeamsColor teamsColor = teamsColorBuilder
                .withShirtColor1(expectedShirtColor1)
                .withShirtColor2(expectedShirtColor2)
                .withGoalkeeperColor1(expectedGoalkeeperColor1)
                .withGoalkeeperColor2(expectedGoalkeeperColor2)
                .build();

        assertEquals(expectedShirtColor1, teamsColor.shirtColor1());
        assertEquals(expectedShirtColor2, teamsColor.shirtColor2());
        assertEquals(expectedGoalkeeperColor1, teamsColor.goalkeeperColor1());
        assertEquals(expectedGoalkeeperColor2, teamsColor.goalkeeperColor2());
    }

    @Test
    public void testTeamsColorBuilder() {
        TeamsColorBuilder teamsColorBuilder = new TeamsColorBuilder();
        String expectedShirtColor1FrenchName = "BLEU";
        String expectedShirtColor2FrenchName = "BLANC";
        String expectedGoalkeeperColor1FrenchName = "VERT";
        String expectedGoalkeeperColor2FrenchName = "JAUNE";

        TeamsColor teamsColor = teamsColorBuilder
                .withShirtColor1(builder -> builder.withFrenchName(expectedShirtColor1FrenchName))
                .withShirtColor2(builder -> builder.withFrenchName(expectedShirtColor2FrenchName))
                .withGoalkeeperColor1(builder -> builder.withFrenchName(expectedGoalkeeperColor1FrenchName))
                .withGoalkeeperColor2(builder -> builder.withFrenchName(expectedGoalkeeperColor2FrenchName))
                .build();

        assertEquals(TeamColor.BLUE, teamsColor.shirtColor1());
        assertEquals(TeamColor.WHITE, teamsColor.shirtColor2());
        assertEquals(TeamColor.GREEN, teamsColor.goalkeeperColor1());
        assertEquals(TeamColor.YELLOW, teamsColor.goalkeeperColor2());
    }
}