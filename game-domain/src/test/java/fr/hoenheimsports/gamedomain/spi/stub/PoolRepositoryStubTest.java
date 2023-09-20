package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PoolRepositoryStubTest {

    private PoolRepositoryStub poolRepositoryStub;

    @BeforeEach
    void setUp() {
        poolRepositoryStub = new PoolRepositoryStub();
    }

    @Test
    void testSave() {
        Competition competition = new Competition("Champions League");
        Pool pool = new Pool("P1", "Pool A", competition);

        Pool savedPool = poolRepositoryStub.save(pool);

        assertEquals(pool, savedPool, "The saved pool should match the original pool");
    }

    @Test
    void testFindById() {
        Competition competition = new Competition("Champions League");
        Pool pool = new Pool("P1", "Pool A", competition);

        poolRepositoryStub.save(pool);
        Optional<Pool> foundPool = poolRepositoryStub.findById("P1");

        assertTrue(foundPool.isPresent(), "Pool should be found by code");
        assertEquals(pool, foundPool.get(), "Found pool should match the saved pool");
    }

}

