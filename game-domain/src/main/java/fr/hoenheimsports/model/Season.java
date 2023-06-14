package fr.hoenheimsports.model;

import java.time.LocalDate;
import java.util.Objects;

public record Season(String name, LocalDate startDate, LocalDate endDate) {
    public static final Season SEASON_2022_2023 = new Season("SEASON_2022_2023",LocalDate.of(2022,8,1),LocalDate.of(2023,7,31));
    /**
     *
     * @param name is Season ID
     * @param startDate
     * @param endDate
     */
    public Season {
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(startDate,"startDate should be not null");
        Objects.requireNonNull(endDate,"endDate should be not null");
    }
}
