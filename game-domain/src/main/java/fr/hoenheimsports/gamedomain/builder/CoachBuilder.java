package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;

import java.util.UUID;
import java.util.function.Consumer;

public class CoachBuilder {
    public static CoachBuilder builder() {
        return new CoachBuilder();
    }
    private UUID id;
    private String name;
    private PhoneNumber phoneNumber;

    private CoachRepository coachRepository;

    public CoachBuilder addIdGeneratorFromRepository(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
        return this;
    }

    public CoachBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CoachBuilder withPhoneNumber(Consumer<PhoneNumberBuilder> phoneNumberBuilderFunction) {
        PhoneNumberBuilder phoneNumberBuilder = new PhoneNumberBuilder();
        phoneNumberBuilderFunction.accept(phoneNumberBuilder);
        this.phoneNumber = phoneNumberBuilder.build();
        return this;
    }

    public CoachBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Coach build() {
        if(this.coachRepository != null) {
            var optionalCoach = this.coachRepository.findCoachByKeys(name);
            if (optionalCoach.isPresent()) {
                this.id = optionalCoach.get().id();
            }
        }
        if(this.id == null) {
            this.id = UUID.randomUUID();
        }
        return new Coach(this.id, this.name, this.phoneNumber);
    }
}
