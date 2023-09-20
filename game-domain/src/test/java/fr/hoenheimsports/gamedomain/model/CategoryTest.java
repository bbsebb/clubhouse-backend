package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void testValidCategory() {
        Category category = new Category("Senior", 25, true);
        assertEquals("Senior", category.name());
        assertEquals(25, category.age());
        assertTrue(category.isMaxAge());
    }

    @Test
    void testUnknownCategory() {
        Category category = Category.UNKNOWN;
        assertEquals("unknown", category.name());
        assertEquals(0, category.age());
        assertTrue(category.isMaxAge());
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(NullPointerException.class, () -> new Category(null, 10, true));
    }

    @Test
    void testNegativeAgeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Youth", -1, false));
    }

    @Test
    void testZeroAge() {
        Category category = new Category("Newborn", 0, false);
        assertEquals("Newborn", category.name());
        assertEquals(0, category.age());
        assertFalse(category.isMaxAge());
    }
}