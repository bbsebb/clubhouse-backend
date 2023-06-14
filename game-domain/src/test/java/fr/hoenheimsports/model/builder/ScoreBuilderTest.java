package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Score;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBuilderTest {
    @Test
    public void testScoreBuilder() {
        ScoreBuilder scoreBuilder = new ScoreBuilder();
        int expectedHomeScore = 5;
        int expectedVisitingScore = 3;

        Score score = scoreBuilder
                .withHomeScore(expectedHomeScore)
                .withVisitingScore(expectedVisitingScore)
                .build();

        assertEquals(expectedHomeScore, score.homeScore());
        assertEquals(expectedVisitingScore, score.visitingScore());
    }
}