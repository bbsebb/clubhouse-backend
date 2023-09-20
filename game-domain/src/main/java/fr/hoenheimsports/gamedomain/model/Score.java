package fr.hoenheimsports.gamedomain.model;
/**
 * Represents the score for a game.
 * <p>
 * This record holds the scores for both the home and visiting teams. It ensures
 * that the scores are non-negative.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>{@code homeScore} represents the score of the home team and must be 0 or greater.</li>
 *   <li>{@code visitingScore} represents the score of the visiting team and must be 0 or greater.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <p>
 * Typically used to capture the final score of a game, but can be updated dynamically
 * as the game progresses.
 * </p>
 */
public record Score(int homeScore, int visitingScore) {
    /**
     * A constant representing the default starting score of a no played game .
     */
    public static final Score DEFAULT = new Score(0,0);
    /**
     * Constructs an instance of {@code Score} after validating the provided scores.
     */
    public Score {
        if(homeScore<0 || visitingScore<0) {
            throw new IllegalArgumentException("Score must be equal or greater than 0");
        }
    }
}
