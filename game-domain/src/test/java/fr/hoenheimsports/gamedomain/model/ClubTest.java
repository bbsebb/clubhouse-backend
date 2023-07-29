package fr.hoenheimsports.gamedomain.model;

import fr.hoenheimsports.gamedomain.model.Club;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ClubTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Club(null, "Name",new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Club("Code", null,new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Club("Code", "Name",null));
    }
}