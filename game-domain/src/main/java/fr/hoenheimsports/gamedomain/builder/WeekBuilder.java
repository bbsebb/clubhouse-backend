package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Week;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekBuilder {
    public static WeekBuilder builder() {
        return new WeekBuilder();
    }
    private int year;
    private int week;

    public WeekBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public WeekBuilder withWeek(int week) {
        this.week = week;
        return this;
    }

    public WeekBuilder withLocalDate(LocalDate localDate) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        this.week = localDate.get(weekFields.weekOfWeekBasedYear());
        this.year = localDate.getYear();
        return this;
    }

    public Week build() {
        return new Week(this.year,this.week);
    }

}
