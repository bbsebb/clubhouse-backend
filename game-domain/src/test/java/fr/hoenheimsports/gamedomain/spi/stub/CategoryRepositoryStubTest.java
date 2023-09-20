package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryStubTest {

    private CategoryRepositoryStub categoryRepositoryStub;

    @BeforeEach
    void setUp() {
        categoryRepositoryStub = new CategoryRepositoryStub();
    }

    @Test
    void testSave() {
        Category category = new Category("Sports", 10, false);
        Category savedCategory = categoryRepositoryStub.save(category);

        assertEquals(category, savedCategory, "The saved category should match the original category");
    }

    @Test
    void testFindAll() {
        Category sportsCategory = new Category("Sports", 10, false);
        Category musicCategory = new Category("Music", 5, false);

        categoryRepositoryStub.save(sportsCategory);
        categoryRepositoryStub.save(musicCategory);

        var allCategories = categoryRepositoryStub.findAll();

        assertTrue(allCategories.contains(sportsCategory), "The list should contain the sports category");
        assertTrue(allCategories.contains(musicCategory), "The list should contain the music category");
        assertEquals(2, allCategories.size(), "There should be two categories in the list");
    }

    @Test
    void testFindByNameExisting() {
        Category sportsCategory = new Category("Sports", 10, false);
        categoryRepositoryStub.save(sportsCategory);

        Optional<Category> foundCategory = categoryRepositoryStub.findById("Sports");

        assertTrue(foundCategory.isPresent(), "The category should be found");
        assertEquals(sportsCategory, foundCategory.get(), "The found category should match the saved category");
    }

    @Test
    void testFindByNameNonExisting() {
        Optional<Category> foundCategory = categoryRepositoryStub.findById("NonExisting");
        assertFalse(foundCategory.isPresent(), "No category should be found for a non-existing name");
    }
}