package fr.hoenheimsports.gamedomain.model;

import fr.hoenheimsports.gamedomain.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    public void testConstructorWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new Category(null,0,true));
        assertThrows(IllegalArgumentException.class, () -> new Category("test",-1,true));
    }
}