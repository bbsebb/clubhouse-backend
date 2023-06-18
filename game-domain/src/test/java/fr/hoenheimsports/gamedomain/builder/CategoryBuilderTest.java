package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Category;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryBuilderTest {
    @Test
    public void testBuilderMethod() {
        CategoryBuilder categoryBuilder = CategoryBuilder.builder();
        assertNotNull(categoryBuilder);
    }
    @Test
    public void testCategoryBuilder() {
        CategoryBuilder categoryBuilder = new CategoryBuilder();
        UUID expectedId = UUID.randomUUID();
        String expectedName = "-18 ans";

        Category category = categoryBuilder
                .withName(expectedName)
                .build();

        assertEquals(expectedName, category.name());
    }


}