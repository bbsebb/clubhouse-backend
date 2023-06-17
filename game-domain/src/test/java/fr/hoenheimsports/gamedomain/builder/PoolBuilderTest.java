package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Pool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoolBuilderTest {
    @Test
    public void testPoolBuilder() {
        PoolBuilder poolBuilder = new PoolBuilder();
        String expectedNumber = "A";
        String expectedName = "Pool A";

        Pool pool = poolBuilder
                .withNumber(expectedNumber)
                .withName(expectedName)
                .build();

        assertEquals(expectedNumber, pool.number());
        assertEquals(expectedName, pool.name());
    }
}