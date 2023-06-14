package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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