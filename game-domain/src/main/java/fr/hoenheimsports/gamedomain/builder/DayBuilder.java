package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Day;

public class DayBuilder {
    public static DayBuilder builder() {
        return new DayBuilder();
    }
    private int number;

    public DayBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public Day build() {
        return new Day(number);
    }
}
