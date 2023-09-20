package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
/**
 * Represents a specific week within a year.
 *
 * <p>
 * The `Week` record encapsulates information about a specific week of a year, identified by its year and the week number.
 * Weeks are numbered from 1 to 52. The record also provides functionality to instantiate a `Week` instance using a {@link LocalDate}.
 * </p>
 *
 * Example:
 * <pre>
 *     Week currentWeek = new Week(LocalDate.now());
 *     Week specifiedWeek = new Week(2023, 5);
 * </pre>
 *
 * Note: Week numbers are based on the default locale and may vary based on the rules of specific cultures.
 */
public record Week(int year, int week) {
    /**
     * A constant representing the current week based on today's date.
     */
    public static final Week NOW = new Week(LocalDate.now());
    /**
     * Constructs a new instance of Week.
     *
     * <p>
     * The year should be recent (greater than or equal to 1900) and the week number should be in the range [1, 52].
     * </p>
     *
     * @throws IllegalArgumentException if the provided year is not recent or if the week number is out of range.
     */
    public Week {
        if(year<1900) {
            throw new IllegalArgumentException("year shoud be recent");
        }
        if(week<1 || week>52) {
            throw new IllegalArgumentException("week shoud be between 1 and 52");
        }
    }
    /**
     * Constructs a Week instance using a specified {@link LocalDate}. The year is directly taken from the given date,
     * while the week number is computed based on the date.
     *
     * @param localDate The date to derive the week from.
     */
    public Week(LocalDate localDate) {
        this(localDate.getYear(), getWeekNumber(localDate));
    }
    /**
     * Computes the week number for a given {@link LocalDate}.
     *
     * @param localDate The date for which the week number is to be computed.
     * @return The week number of the given date.
     */
    public static int getWeekNumber(LocalDate localDate) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return localDate.get(weekFields.weekOfWeekBasedYear());
    }
}
