package fr.hoenheimsports.gamedomain.model;
/**
 * Represents a specific day characterized by its number.
 * <p>
 * The day is generally used in contexts where a sequence of days is relevant, such as multi-day tournaments or events.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code number} must be equal to or greater than 0.</li>
 * </ul>
 */
public record Day(int number) {
    /**
     * A constant representing a game or event that occurs on a single day.
     */
    public static final Day SINGLE_DAY_GAME = new Day(0);
    /**
     * Constructs an instance of {@code Day} after validating the provided day number.
     */
    public Day {
        if(number<0) {
            throw new IllegalArgumentException("The day must be equal or greater than 0");
        }
    }
}
