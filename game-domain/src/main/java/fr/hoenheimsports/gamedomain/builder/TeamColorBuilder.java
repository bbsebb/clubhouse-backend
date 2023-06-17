package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.TeamColor;

public class TeamColorBuilder {
    private String frenchName;

    public TeamColorBuilder withFrenchName(String frenchName) {
        this.frenchName = frenchName;
        return this;
    }

    public TeamColor build() {
        return TeamColor.getByFrenchName(frenchName);
    }
}
