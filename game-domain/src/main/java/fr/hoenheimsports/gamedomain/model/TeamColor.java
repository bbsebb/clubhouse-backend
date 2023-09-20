package fr.hoenheimsports.gamedomain.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 * Represents the colors available for a team. Each color is associated with its name in French.
 * <p>
 * This enum allows you to get a team color based on its name in French and provides a mapping between English and French color names.
 * </p>
 * Example usage:
 * <pre>
 *     TeamColor color = TeamColor.getByFrenchName("BLANC");
 *     System.out.println(color);  // Outputs: WHITE
 * </pre>
 */
public enum TeamColor {
    WHITE("BLANC"),
    YELLOW("JAUNE"),
    RED("ROUGE"),
    BLACK("NOIR"),
    GREEN("VERT"),
    BLUE("BLEU"),
    VIOLET("VIOLET"),
    GREY("GRIS"),
    BROWN("MARRON"),
    PINK("ROSE"),
    ORANGE("ORANGE"),
    SKY_BLUE("BLEU CIEL"),
    NAVY_BLUE("BLEU MARINE"),
    TURQUOISE("TURQUOISE"),
    BURGUNDY("BORDEAUX"),
    BEIGE("BEIGE"),
    GARNET("GRENAT"),
    PURPLE("MAUVE"),
    UNKNOWN("INCONNU");


    private final String frenchName;
    /**
     * Constructs a new team color with its associated French name.
     *
     * @param frenchName The name of the team color in French.
     */
    TeamColor(String frenchName) {
        this.frenchName = frenchName;
    }
    /**
     * Returns the French name of the team color.
     *
     * @return The French name of the team color.
     */
    public String getFrenchName() {
        return this.frenchName;
    }
    /**
     * Returns the team color associated with the given French name.
     *
     * @param frenchName The name of the team color in French.
     * @return The associated team color.
     * @throws NoSuchElementException if no matching color is found.
     */
    static public TeamColor getByFrenchName(String frenchName) {
        return Arrays.stream(TeamColor.values()).filter(t -> t.getFrenchName().equals(frenchName.trim().toUpperCase())).findFirst().orElseThrow(() -> new NoSuchElementException("Couleur absente : -" + frenchName.trim().toUpperCase() + "-"));
    }
}
