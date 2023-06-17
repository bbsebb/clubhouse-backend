package fr.hoenheimsports.gamedomain.model;
import fr.hoenheimsports.gamedomain.model.Score;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @Test
    public void testConstructorWithNegativeScores() {
        assertThrows(IllegalArgumentException.class, () -> new Score(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> new Score(2, -1));
    }
}