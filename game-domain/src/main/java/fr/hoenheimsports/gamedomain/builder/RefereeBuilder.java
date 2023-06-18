package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;

import java.util.UUID;

public class RefereeBuilder {
    private RefereeRepository refereeRepository;

    public static RefereeBuilder builder() {
        return new RefereeBuilder();
    }
    private UUID id;
    private String name;

    public RefereeBuilder addIdGeneratorFromRepository(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
        return this;
    }
    public RefereeBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RefereeBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public Referee build() {
        if(this.refereeRepository != null) {
            var optionalReferee = this.refereeRepository.findRefereeByKeys(name);
            if (optionalReferee.isPresent()) {
                this.id = optionalReferee.get().id();
            }
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Referee(id, name);
    }
}
