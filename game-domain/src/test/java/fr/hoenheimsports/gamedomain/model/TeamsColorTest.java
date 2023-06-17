package fr.hoenheimsports.gamedomain.model;
import fr.hoenheimsports.gamedomain.model.TeamsColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeamsColorTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertDoesNotThrow(() -> new TeamsColor(null, null, null, null));
    }
}