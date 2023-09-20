package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoolTest {
    @Test
    public void testConstructorValidInputs() {
        Competition competition = new Competition("Test Competition");
        Pool pool = new Pool("TestCode", "TestName", competition);

        assertEquals("TestCode", pool.code());
        assertEquals("TestName", pool.name());
        assertEquals(competition, pool.competition());
    }

    @Test
    public void testConstructorWithNullCode() {
        Competition competition = new Competition("Test Competition");

        assertThrows(NullPointerException.class, () -> new Pool(null, "TestName", competition));
    }

    @Test
    public void testConstructorWithNullName() {
        Competition competition = new Competition("Test Competition");

        assertThrows(NullPointerException.class, () -> new Pool("TestCode", null, competition));
    }

    @Test
    public void testConstructorWithNullCompetition() {
        assertThrows(NullPointerException.class, () -> new Pool("TestCode", "TestName", null));
    }

    @Test
    public void testUnknownPool() {
        Pool unknownPool = Pool.UNKNOWN;

        assertEquals("unknown", unknownPool.code());
        assertEquals("unknown", unknownPool.name());
        assertEquals(Competition.UNKNOWN, unknownPool.competition());
    }}