package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Club;

public class ClubBuilder {
    public static ClubBuilder builder() {
        return new ClubBuilder();
    }
    private String code;
    private String name;

    public ClubBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ClubBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Club build() {
        return new Club(code, name);
    }
}
