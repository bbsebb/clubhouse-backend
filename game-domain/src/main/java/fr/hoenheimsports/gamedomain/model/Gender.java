package fr.hoenheimsports.gamedomain.model;
/**
 * Represents the gender category.
 * <p>
 * This enum provides a way to categorize gender, typically used in contexts like sports teams or events.
 * </p>
 *
 * <h2>Available Values:</h2>
 * <ul>
 *   <li>{@link Gender#FEMALE} - Represents the female gender.</li>
 *   <li>{@link Gender#MALE} - Represents the male gender.</li>
 *   <li>{@link Gender#MIXED} - Represents a mixed gender category, usually comprising both male and female.</li>
 *   <li>{@link Gender#UNKNOWN} - Represents an unknown or unspecified gender category.</li>
 * </ul>
 */
public enum Gender {
    FEMALE,
    MALE,
    MIXED,
    UNKNOWN
}
