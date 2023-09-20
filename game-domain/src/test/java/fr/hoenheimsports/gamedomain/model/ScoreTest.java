package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreTest {
    @Test
    public void testConstructorValidInput() {
        Score score = new Score(10, 5);

        assertEquals(10, score.homeScore());
        assertEquals(5, score.visitingScore());
    }

    @Test
    public void testConstructorNegativeHomeScore() {
        assertThrows(IllegalArgumentException.class, () -> new Score(-1, 5));
    }

    @Test
    public void testConstructorNegativeVisitingScore() {
        assertThrows(IllegalArgumentException.class, () -> new Score(10, -5));
    }

    @Test
    public void testDefaultConstant() {
        Score defaultScore = Score.DEFAULT;

        assertEquals(0, defaultScore.homeScore());
        assertEquals(0, defaultScore.visitingScore());
    }

}