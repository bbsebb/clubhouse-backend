package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RefereesTest {
    private final Referee validReferee1 = new Referee(UUID.randomUUID(), "Referee1 Name");
    private final Referee validReferee2 = new Referee(UUID.randomUUID(), "Referee2 Name");
    private final Referee validReferee3 = new Referee(UUID.randomUUID(), "Referee3 Name");
    private final Referee validReferee4 = new Referee(UUID.randomUUID(), "Referee4 Name");

    @Test
    public void testConstructorValidInput() {
        Referees referees = new Referees(validReferee1, validReferee2, validReferee3, validReferee4);

        assertEquals(validReferee1, referees.designatedReferee1());
        assertEquals(validReferee2, referees.designatedReferee2());
        assertEquals(validReferee3, referees.officiatingReferee1());
        assertEquals(validReferee4, referees.officiatingReferee2());
    }

    @Test
    public void testConstructorNullDesignatedReferee1() {
        assertThrows(NullPointerException.class, () -> new Referees(null, validReferee2, validReferee3, validReferee4));
    }

    @Test
    public void testConstructorNullDesignatedReferee2() {
        assertThrows(NullPointerException.class, () -> new Referees(validReferee1, null, validReferee3, validReferee4));
    }

    @Test
    public void testConstructorNullOfficiatingReferee1() {
        assertThrows(NullPointerException.class, () -> new Referees(validReferee1, validReferee2, null, validReferee4));
    }

    @Test
    public void testConstructorNullOfficiatingReferee2() {
        assertThrows(NullPointerException.class, () -> new Referees(validReferee1, validReferee2, validReferee3, null));
    }

    @Test
    public void testUnknownConstant() {
        Referees unknown = Referees.UNKNOWN;

        assertEquals(Referee.UNKNOWN, unknown.designatedReferee1());
        assertEquals(Referee.UNKNOWN, unknown.designatedReferee2());
        assertEquals(Referee.UNKNOWN, unknown.officiatingReferee1());
        assertEquals(Referee.UNKNOWN, unknown.officiatingReferee2());
    }
}