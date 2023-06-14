package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Day;

public class DayBuilder {
    private int number;

    public DayBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public Day build() {
        return new Day(number);
    }
}
