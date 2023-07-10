package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public record Week(int year, int week) {
    public static final Week NOW = new Week(LocalDate.now());

    public Week {
        if(year<1900) {
            throw new IllegalArgumentException("year shoud be recent");
        }
        if(week<1 || week>52) {
            throw new IllegalArgumentException("week shoud be between 1 and 52");
        }
    }

    public Week(LocalDate localDate) {
        this(localDate.getYear(), getWeekNumber(localDate));
    }

    private static int getWeekNumber(LocalDate localDate) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return localDate.get(weekFields.weekOfWeekBasedYear());
    }
}
