package fr.hoenheimsports.gamedomain.model;

import fr.hoenheimsports.gamedomain.model.FDME;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FDMETest {
    @Test
    public void testConstructorWithNullParameter() {
        assertThrows(NullPointerException.class, () -> new FDME(null));
    }
}