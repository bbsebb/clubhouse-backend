package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Club;

public class ClubBuilder {
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
