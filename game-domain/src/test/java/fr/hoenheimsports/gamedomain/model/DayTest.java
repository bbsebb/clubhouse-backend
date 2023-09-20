package fr.hoenheimsports.gamedomain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DayTest {
    @Test
    public void testConstructorValidInput() {
        Day day = new Day(1);

        assertEquals(1, day.number());
    }

    @Test
    public void testConstructorNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Day(-1));
    }

    @Test
    public void testSingleDayGameConstant() {
        Day singleDay = Day.SINGLE_DAY_GAME;

        assertEquals(0, singleDay.number());
    }}