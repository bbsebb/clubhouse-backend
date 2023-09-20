package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
/**
 * Represents the colors used by a team, including the primary and secondary shirt colors for field players,
 * as well as the primary and secondary colors for goalkeepers.
 *
 * <p>
 * A team typically has a primary (or home) color and a secondary (or away) color. Goalkeepers have their
 * own distinct colors which differentiate them from the field players.
 * </p>
 *
 * Example:
 * <pre>
 *     TeamsColor teamColors = new TeamsColor(TeamColor.BLUE, TeamColor.WHITE, TeamColor.RED, TeamColor.GREEN);
 *     TeamColor primaryShirtColor = teamColors.shirtColor1();  // BLUE
 * </pre>
 */
public record TeamsColor(TeamColor shirtColor1,
                         TeamColor shirtColor2,
                         TeamColor goalkeeperColor1,
                         TeamColor goalkeeperColor2) {
    /**
     * A constant representing unknown team colors. All colors are set to {@link TeamColor#UNKNOWN}.
     */
    public static final TeamsColor UNKNOWN = new TeamsColor(TeamColor.UNKNOWN,TeamColor.UNKNOWN,TeamColor.UNKNOWN,TeamColor.UNKNOWN);

    /**
     * Constructs a new instance of TeamsColor.
     * <p>
     * If any color is not provided or is null, it will be set to {@link TeamColor#UNKNOWN}.
     * </p>
     */
    public TeamsColor {
        shirtColor1 = Objects.requireNonNullElse(shirtColor1,TeamColor.UNKNOWN);
        shirtColor2 = Objects.requireNonNullElse(shirtColor2,TeamColor.UNKNOWN);
        goalkeeperColor1 = Objects.requireNonNullElse(goalkeeperColor1,TeamColor.UNKNOWN);
        goalkeeperColor2 = Objects.requireNonNullElse(goalkeeperColor2,TeamColor.UNKNOWN);
    }
}
