package fr.hoenheimsports.repository.game.entity.game;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum TeamColorEntity {
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
    TeamColorEntity(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getFrenchName() {
        return this.frenchName;
    }

    static public TeamColorEntity getByFrenchName(String frenchName) {
        return Arrays.stream(TeamColorEntity.values()).filter(t -> t.getFrenchName().equals(frenchName.trim().toUpperCase())).findFirst().orElseThrow(() -> new NoSuchElementException("Couleur absente : -" + frenchName.trim().toUpperCase() + "-"));
    }
}
