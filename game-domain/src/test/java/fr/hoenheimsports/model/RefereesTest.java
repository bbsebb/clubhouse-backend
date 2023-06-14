package fr.hoenheimsports.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RefereesTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Referees(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Referees(Referee.UNKNOWN, null, null, null));
        assertThrows(NullPointerException.class, () -> new Referees(Referee.UNKNOWN, Referee.UNKNOWN, null, null));
        assertThrows(NullPointerException.class, () -> new Referees(Referee.UNKNOWN, Referee.UNKNOWN, Referee.UNKNOWN, null));
    }
}