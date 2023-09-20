package fr.hoenheimsports.gamedomain.model;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
    private final UUID validId = UUID.randomUUID();
    private final String validName = "Referee Name";

    @Test
    public void testConstructorValidInput() {
        Referee referee = new Referee(validId, validName);

        assertEquals(validId, referee.id());
        assertEquals(validName, referee.name());
    }

    @Test
    public void testConstructorNullId() {
        assertThrows(NullPointerException.class, () -> new Referee(null, validName));
    }

    @Test
    public void testConstructorNullName() {
        assertThrows(NullPointerException.class, () -> new Referee(validId, null));
    }

    @Test
    public void testUnknownConstant() {
        Referee unknown = Referee.UNKNOWN;

        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), unknown.id());
        assertEquals("unknown", unknown.name());
    }

}