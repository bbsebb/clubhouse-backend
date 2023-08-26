package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;

import java.util.HashSet;
import java.util.Set;

public class ClubBuilder {
    public static ClubBuilder builder() {
        return new ClubBuilder();
    }
    private String code;
    private String name;

    private Set<Hall> halles = new HashSet<>();

    public ClubBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ClubBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClubBuilder addHalle(Hall halle) {
        this.halles.add(halle);
        return this;
    }

    public Club build() {
        return new Club(code, name,halles);
    }
}
