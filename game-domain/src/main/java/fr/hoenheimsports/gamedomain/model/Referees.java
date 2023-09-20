package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents a group of referees assigned for a particular match or event.
 * <p>
 * This record encapsulates both the initially designated referees and those who actually officiated the match.
 * While typically the designated referees are the ones officiating, there might be changes due to unforeseen circumstances.
 * This distinction helps track these changes.
 * </p>
 *
 * <h2>Roles:</h2>
 * <ul>
 *   <li><strong>Designated Referees</strong>: Initially chosen referees for a match. However, they might not officiate the match due to unforeseen events like last-minute unavailability.</li>
 *   <li><strong>Officiating Referees</strong>: The referees who actually officiate on the day of the match.</li>
 * </ul>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code designatedReferee1} is one of the two main referees initially chosen for a match and must not be {@code null}.</li>
 *   <li>{@code designatedReferee2} is the second of the two main referees initially chosen and must not be {@code null}.</li>
 *   <li>{@code officiatingReferee1} is one of the referees who actually officiated the match and must not be {@code null}.</li>
 *   <li>{@code officiatingReferee2} is the second referee who actually officiated the match and must not be {@code null}.</li>
 * </ul>
 */
public record Referees(Referee designatedReferee1, Referee designatedReferee2, Referee officiatingReferee1, Referee officiatingReferee2) {
    /**
     * A constant representing an unknown referees group.
     */
    public static final Referees UNKNOWN = new Referees(Referee.UNKNOWN,Referee.UNKNOWN,Referee.UNKNOWN,Referee.UNKNOWN);
    /**
     * Constructs an instance of {@code Referees} after validating the provided referee assignments.
     */
    public Referees {
        Objects.requireNonNull(designatedReferee1, "designatedReferee1 should not be null");
        Objects.requireNonNull(designatedReferee2, "designatedReferee2 should not be null");
        Objects.requireNonNull(officiatingReferee1, "officiatingReferee1 should not be null");
        Objects.requireNonNull(officiatingReferee2, "officiatingReferee2 should not be null");
    }
}
