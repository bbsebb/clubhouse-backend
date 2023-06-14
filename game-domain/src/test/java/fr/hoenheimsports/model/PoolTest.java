package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoolTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Pool(null, "Name"));
        assertThrows(NullPointerException.class, () -> new Pool("Number", null));
    }

    @Test
    public void testConstructorWithUnknownValues() {
        Pool pool = Pool.UNKNOWN;
        assertEquals("unknown", pool.number());
        assertEquals("unknown", pool.name());
    }

    @Test
    public void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> new Pool("Number", "Name"));
    }
}