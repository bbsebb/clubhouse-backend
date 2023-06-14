package fr.hoenheimsports.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
    UNKNOWN("INCONNUE");

    private final String frenchName;
    private TeamColor(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getFrenchName() {
        return this.frenchName;
    }

    static public TeamColor getByFrenchName(String frenchName) {
        return Arrays.stream(TeamColor.values()).filter(t -> t.getFrenchName().equals(frenchName.trim().toUpperCase())).findFirst().orElseThrow(() -> new NoSuchElementException("Couleur absente : -" + frenchName.trim().toUpperCase() + "-"));
    }
}
