package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DayBuilderTest {
    @Test
    public void testBuilderMethod() {
        DayBuilder dayBuilder = DayBuilder.builder();
        assertNotNull(dayBuilder);
    }

    @Test
    public void testBuild() {
        int number = 1;

        Day day = new DayBuilder()
                .withNumber(number)
                .build();

        Assertions.assertEquals(number, day.number());
    }
}