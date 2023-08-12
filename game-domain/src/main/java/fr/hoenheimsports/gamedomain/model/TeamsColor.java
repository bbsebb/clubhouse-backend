package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;

public record TeamsColor(TeamColor shirtColor1,
                         TeamColor shirtColor2,
                         TeamColor goalkeeperColor1,
                         TeamColor goalkeeperColor2) {
    public static final TeamsColor UNKNOWN = new TeamsColor(TeamColor.UNKNOWN,TeamColor.UNKNOWN,TeamColor.UNKNOWN,TeamColor.UNKNOWN);
    public TeamsColor {
        Objects.requireNonNullElse(shirtColor1,TeamColor.UNKNOWN);
        Objects.requireNonNullElse(shirtColor2,TeamColor.UNKNOWN);
        Objects.requireNonNullElse(goalkeeperColor1,TeamColor.UNKNOWN);
        Objects.requireNonNullElse(goalkeeperColor2,TeamColor.UNKNOWN);
    }
}
