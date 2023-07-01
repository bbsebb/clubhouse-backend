package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.UUID;

public record Week(int year, int week) {
    public static final Week NOW = new Week(LocalDate.now());

    public Week(int year, int week) {
        this.year = year;
        this.week = week;
    }

    public Week(LocalDate localDate) {
        this(localDate.getYear(), getWeekNumber(localDate));
    }

    private static int getWeekNumber(LocalDate localDate) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return localDate.get(weekFields.weekOfWeekBasedYear());
    }
}
