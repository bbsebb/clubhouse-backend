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
        int age = 18;
        boolean isMaxAge = true;
        String expectedName = "-18 ans";

        Category category = categoryBuilder
                .withAge(age)
                .withIsMaxAge(isMaxAge)
                .build();


        assertEquals(age, category.age());
        assertEquals(isMaxAge, category.isMaxAge());
        assertEquals(expectedName, category.name());
    }


}