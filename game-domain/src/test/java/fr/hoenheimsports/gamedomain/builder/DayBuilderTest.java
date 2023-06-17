package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayBuilderTest {
    @Test
    public void testBuild() {
        int number = 1;

        Day day = new DayBuilder()
                .withNumber(number)
                .build();

        Assertions.assertEquals(number, day.number());
    }
}