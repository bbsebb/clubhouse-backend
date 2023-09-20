package fr.hoenheimsports.gamedomain.model;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Represents a season for games, typically bounded by a start and end date.
 * <p>
 * This record provides a standardized means of defining and representing a game season within a specific time frame.
 * The {@code name} parameter, constructed from the start and end dates, serves as a unique identifier for the season.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code name}, {@code startDate}, and {@code endDate} must not be null.</li>
 *   <li>{@code name} should typically be constructed from {@code startDate} and {@code endDate} and serves as the unique identifier.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <p>
 * This record can be used in various scenarios where game seasons need to be defined, tracked, or presented within
 * a sports management system or related applications.
 * </p>
 *
 * @param name A unique identifier for the season, typically constructed from the start and end dates.
 * @param startDate The starting date of the season.
 * @param endDate The ending date of the season.
 */
public record Season(String name, LocalDate startDate, LocalDate endDate) {
    /**
     * Represents the 2022-2023 game season.
     */
    public static final Season SEASON_2022_2023 = new Season("SEASON_2022_2023",LocalDate.of(2022,8,1),LocalDate.of(2023,7,31));
    /**
     * Constructs a {@code Season} instance after validating the provided parameters.
     */
    public Season {
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(startDate,"startDate should be not null");
        Objects.requireNonNull(endDate,"endDate should be not null");
    }
}
