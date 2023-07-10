package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

@Embeddable
public class WeekEntity {
    private int year;
    private int week;

    public WeekEntity() {
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WeekEntity) obj;
        return this.year == that.year &&
                this.week == that.week;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, week);
    }

    @Override
    public String toString() {
        return "WeekEntity[" +
                "year=" + year + ", " +
                "week=" + week + ']';
    }


}
