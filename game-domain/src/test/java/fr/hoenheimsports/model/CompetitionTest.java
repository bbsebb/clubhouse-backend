package fr.hoenheimsports.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Competition(null, List.of(new Pool("0", "Pool"))));
        assertThrows(NullPointerException.class, () -> new Competition("Name", null));
    }
}