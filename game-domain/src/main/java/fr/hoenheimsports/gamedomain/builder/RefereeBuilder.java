package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import fr.hoenheimsports.gamedomain.model.Referee;

import java.util.UUID;
import java.util.function.Consumer;

public class RefereeBuilder {
    public static RefereeBuilder builder() {
        return new RefereeBuilder();
    }
    private UUID id;
    private String name;


    public RefereeBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RefereeBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public Referee build() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Referee(id, name);
    }
}
